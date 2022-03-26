package com.amm.navigationdraweractivitydata.preferencias;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amm.navigationdraweractivitydata.FragmentListener;

import java.util.ArrayList;

import navigationdraweractivitydata.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreferenciasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreferenciasFragment extends Fragment  implements ColorRVAdapter.ColorsItemClickListener{

    private RecyclerView rvColors;
    private ArrayList<Pair<String,Integer>> bgColors;
    private FragmentListener fragmentListener;
    private Pair<String,Integer> selectedColor;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentListener = (FragmentListener) context;
    }

    public PreferenciasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreferenciasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreferenciasFragment newInstance(String param1, String param2) {
        PreferenciasFragment fragment = new PreferenciasFragment();
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
        return inflater.inflate(R.layout.fragment_preferencias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Instanciamos el recycler view
        rvColors = (RecyclerView) getView().findViewById(R.id.rvColors);

        ColorRVAdapter adapter = new ColorRVAdapter(this);
        rvColors.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvColors.setAdapter(adapter);

        selectedColor = fragmentListener.getSelectedColor();
        if (selectedColor != null){
            TextView tvSelectedColor = view.findViewById(R.id.tvSelectedColor);

            tvSelectedColor.setBackgroundColor(selectedColor.second);
            tvSelectedColor.setText(selectedColor.first.toString());

            if (selectedColor.second == Color.BLUE || selectedColor.second == Color.BLACK){
                tvSelectedColor.setTextColor(Color.WHITE);
            }
            else tvSelectedColor.setTextColor(Color.BLACK);
        }
    }

    @Override
    public void onColorItemClicked(Pair<String,Integer> color) {
        TextView tvSelectedColor = getView().findViewById(R.id.tvSelectedColor);

        tvSelectedColor.setBackgroundColor(color.second);
        tvSelectedColor.setText(color.first.toString());

        if (color.second == Color.BLUE || color.second == Color.BLACK){
            tvSelectedColor.setTextColor(Color.WHITE);
        }
        else tvSelectedColor.setTextColor(Color.BLACK);
        //Toast.makeText(getActivity(), "Item clicked: " + color.first.toString(), Toast.LENGTH_SHORT).show();

        fragmentListener.setSelectedColor(color);
    }
}