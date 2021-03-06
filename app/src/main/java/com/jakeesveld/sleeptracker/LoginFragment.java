package com.jakeesveld.sleeptracker;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends DialogFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    EditText editUsername, editPassword;
    Button buttonSubmit;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {
            getDialog().getWindow().setWindowAnimations(R.style.WindowAnimationStyle);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editPassword = view.findViewById(R.id.edit_password);
        editUsername = view.findViewById(R.id.edit_username);
        buttonSubmit = view.findViewById(R.id.button_login);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();

                JSONObject userInfo = new JSONObject();
                try {
                    userInfo.put("username", username);
                    userInfo.put("password", password);

                    AsyncHandleLogin handleLogin = new AsyncHandleLogin();
                    handleLogin.execute(userInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    class AsyncHandleLogin extends AsyncTask<JSONObject, Integer, String> {

        @Override
        protected String doInBackground(JSONObject... jsonObjects) {
            JSONObject userInfo = jsonObjects[0];
            String result = HomeActivity.dao.loginHandler(userInfo);
            if (result.equals(SleepEntryDAO.SUCCESS)) {
                return SleepEntryDAO.SUCCESS;
            } else {
                return SleepEntryDAO.FAILED;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("request", "success");

            if (getFragmentManager() != null && s.equals(SleepEntryDAO.SUCCESS)) {
                getFragmentManager().beginTransaction().remove(LoginFragment.this).commit();
                Toast.makeText(getContext(), "You have successfully logged in", Toast.LENGTH_SHORT).show();
            } else if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().remove(LoginFragment.this).commit();
                Toast.makeText(getContext(), "Failed to log in. Please check credentials and try again", Toast.LENGTH_LONG).show();
            }

        }
    }
}

