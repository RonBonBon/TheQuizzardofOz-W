package com.arichafamily.thequizzardofozw;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.arichafamily.thequizzardofozw.fragments.AboutFragment;
import com.arichafamily.thequizzardofozw.fragments.QuestionFragment;
import com.arichafamily.thequizzardofozw.fragments.QuizFragment;
import com.arichafamily.thequizzardofozw.fragments.RatingFragment;
import com.arichafamily.thequizzardofozw.fragments.SettingsFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.Scopes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {

    FirebaseAuth.AuthStateListener mAuthStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null) {
                List<AuthUI.IdpConfig> providers = new ArrayList<>();
                AuthUI.IdpConfig email = new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build();
                AuthUI.IdpConfig google = new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).
                        setPermissions(Arrays.asList(Scopes.PROFILE, Scopes.EMAIL)).build();
                providers.add(email);
                providers.add(google);
                Intent intent = AuthUI.getInstance().createSignInIntentBuilder().
                        setLogo(R.drawable.logo).
                        setProviders(providers).
                        build();
                startActivity(intent);
            } else {
                String userName = user.getDisplayName();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Users");
                myRef.setValue(userName);


                ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
                TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);



                SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
                mViewPager.setAdapter(mSectionsPagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new QuizFragment();
                case 1:
                    return new RatingFragment();
                case 2:
                    return new QuestionFragment();
                case 3:
                    return new SettingsFragment();
                case 4:
                    return new AboutFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
