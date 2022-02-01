package com.example.bottomnav;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView6);
        ImageView imageView1 = (ImageView) view.findViewById(R.id.tractor1);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.tractor2);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.tractor3);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.tractor4);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textView1 = (TextView) view.findViewById(R.id.textView11);
        TextView textView2 = (TextView) view.findViewById(R.id.textView10);
        TextView textView3 = (TextView) view.findViewById(R.id.textView12);

        //Context context = getActivity().getApplicationContext();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://krishikhoj-test.herokuapp.com/api/v1/tractors?lat=12.935&long=77.569&tractor_implements=3&page=2&tractorpicture_set=4";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d("response", response.toString());
                    JSONObject object = response.getJSONObject(0);
                    JSONArray array = object.getJSONArray("tractorpicture_set");
                    JSONObject image = array.getJSONObject(0);
                    String imageURL = image.getString("image_url");
                    Glide.with(getActivity()).load(imageURL).into(imageView1);
                    JSONObject image1 = array.getJSONObject(1);
                    String imageURL1 = image1.getString("image_url");
                    Glide.with(getActivity()).load(imageURL1).into(imageView2);
                    JSONObject image2 = array.getJSONObject(1);
                    String imageURL2 = image2.getString("image_url");
                    Glide.with(getActivity()).load(imageURL2).into(imageView3);
                    JSONObject image3 = array.getJSONObject(1);
                    String imageURL3 = image3.getString("image_url");
                    Glide.with(getActivity()).load(imageURL3).into(imageView4);
                    JSONObject object1 = object.getJSONObject("user");
                    String name = object1.getString("name");
                    String imageURL4 = object1.getString("image_url");
                    Glide.with(getActivity()).load(imageURL4).into(imageView);
                    textView.setText(name);
                    String Company = object.getString("make");
                    textView1.setText("Sonalika");
                    String size = object.getString("size_hp");
                    textView2.setText(size + "Horsepower");
                    String vehicle_number = object.getString("vehicle_number");
                    textView3.setText(vehicle_number);


                } catch (JSONException e) {
                    Log.d("error", e.getMessage());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage());
            }
        }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("phone-number", "1234567890");
                params.put("token", "e2d9d043-48f3-45c4-bfab-889043362d92");
                return params;
            }

        };
        requestQueue.add(jsonArrayRequest);
        // Inflate the layout for this fragment
        return view;












    }
}