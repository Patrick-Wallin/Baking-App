package com.example.android.bakingapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.data.RecipeData;
import com.example.android.bakingapp.data.StepData;
import com.example.android.bakingapp.utilities.NetworkUtils;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by piwal on 5/16/2017.
 */

public class RecipeDetailStepInstructionActivityFragment extends Fragment {
    private StepData mStepData;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private Context mContext;
    private int mTotalSteps;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            mStepData = savedInstanceState.getParcelable(getString(R.string.current_step_data));
            mTotalSteps = savedInstanceState.getInt(getString(R.string.number_of_steps));
        }else {
            if (getArguments().containsKey(getString(R.string.current_step_data))) {
                mStepData = getArguments().getParcelable(getString(R.string.current_step_data));
            }
            if (getArguments().containsKey(getString(R.string.number_of_steps))) {
                mTotalSteps = getArguments().getInt(getString(R.string.number_of_steps));
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_recipe_step_detail_fragment,container,false);

        ImageView noVideoImageView = (ImageView) rootView.findViewById(R.id.no_video_image_view);

        if(mStepData != null) {
            mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.playerView);
            NetworkUtils networkUtils = new NetworkUtils(mContext);
            if (networkUtils.isNetworkConnected()) {
                if (mStepData.getVideoUrl().trim().isEmpty()) {
                    mPlayerView.setVisibility(View.GONE);
                    noVideoImageView.setVisibility(View.VISIBLE);
                    new AlertDialog.Builder(mContext)
                            .setTitle(mContext.getString(R.string.error_message_title))
                            .setMessage(mContext.getString(R.string.no_video_link))
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).setIcon(android.R.drawable.ic_dialog_alert).show();
                }else {
                    mPlayerView.setVisibility(View.VISIBLE);
                    noVideoImageView.setVisibility(View.GONE);
                    initializePlayer(Uri.parse(mStepData.getVideoUrl()));
                }
            }else {
                mPlayerView.setVisibility(View.GONE);
                noVideoImageView.setVisibility(View.VISIBLE);
                networkUtils.showAlertMessageAboutNoInternetConnection();
            }

            boolean isThisInLandscape = getResources().getBoolean(R.bool.isItInLandscape);

            TextView mInstructionTextView = (TextView) rootView.findViewById(R.id.instruction_description_text_view);
            if(mInstructionTextView != null)
                mInstructionTextView.setText(mStepData.getDescription());

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                Button previousButton = (Button) rootView.findViewById(R.id.previous_step_button);
                Button nextButton = (Button) rootView.findViewById(R.id.next_step_button);

                final int iStepId = Integer.valueOf(mStepData.getId());
                previousButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int iPreviousStepId = iStepId - 1;
                        if (iPreviousStepId < 0) {
                            new AlertDialog.Builder(mContext)
                                    .setTitle(mContext.getString(R.string.step_title))
                                    .setMessage(mContext.getString(R.string.no_previous_step))
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    }).setIcon(android.R.drawable.ic_dialog_alert).show();
                        } else {
                            StepDirectionListener stepDirectionListener = (StepDirectionListener) mContext;
                            stepDirectionListener.onClickedDirectionButton(iPreviousStepId);
                        }
                    }
                });

                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int iNextStepId = iStepId + 1;
                        if (iNextStepId >= mTotalSteps) {
                            new AlertDialog.Builder(mContext)
                                    .setTitle(mContext.getString(R.string.step_title))
                                    .setMessage(mContext.getString(R.string.no_next_step))
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    }).setIcon(android.R.drawable.ic_dialog_alert).show();
                        } else {
                            StepDirectionListener stepDirectionListener = (StepDirectionListener) mContext;
                            stepDirectionListener.onClickedDirectionButton(iNextStepId);
                        }
                    }
                });
            }
        }

        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            mStepData = savedInstanceState.getParcelable(getString(R.string.current_step_data));
            mTotalSteps = savedInstanceState.getInt(getString(R.string.number_of_steps));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getString(R.string.current_step_data),mStepData);
        outState.putInt(getString(R.string.number_of_steps),mTotalSteps);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mExoPlayer != null)
            mExoPlayer.stop();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer() {
        if(mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            String userAgent = Util.getUserAgent(getContext(), getString(R.string.video_player_name));
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }
}
