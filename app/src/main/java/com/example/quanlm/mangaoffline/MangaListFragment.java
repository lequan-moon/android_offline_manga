package com.example.quanlm.mangaoffline;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quanlm.mangaoffline.adapter.AdtManga;
import com.example.quanlm.mangaoffline.config.Constants;
import com.example.quanlm.mangaoffline.logic.LogicManga;
import com.example.quanlm.mangaoffline.model.Model_Manga;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MangaListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MangaListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MangaListFragment extends Fragment implements AdtManga.OnMangaSelect {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_FILTER = "filter";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mFilter;
    private OnFragmentInteractionListener mListener;

    LogicManga mangaLogic;
    RecyclerView rcvListMangas;
    List<Model_Manga> lstMangas = new ArrayList<>();

    public MangaListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param filter Parameter 1.
     * @return A new instance of fragment MangaListFragment.
     */
    public static MangaListFragment newInstance(String filter) {
        MangaListFragment fragment = new MangaListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FILTER, filter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFilter = getArguments().getString(ARG_FILTER);
        }
        Toast.makeText(getActivity(), "Begin with mFilter:" + mFilter, Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manga_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        rcvListMangas = (RecyclerView) getView().findViewById(R.id.lstMangas);

        // Init Recyclerview list manga
        mangaLogic = new LogicManga(getActivity());
        lstMangas = mangaLogic.getListManga(this.mFilter);
        AdtManga adtManga = new AdtManga(getActivity(), lstMangas, this);
        RecyclerView.LayoutManager layoutMng = new LinearLayoutManager(getActivity());
        rcvListMangas.setLayoutManager(layoutMng);
        rcvListMangas.setItemAnimator(new DefaultItemAnimator());
        rcvListMangas.setAdapter(adtManga);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @Override
    public void onSelect(int mangaID) {
        Toast.makeText(getActivity(), "Select " + mangaID, Toast.LENGTH_SHORT).show();
        Intent itMangaDetail = new Intent(getActivity(), ActMangaDetail.class);
        Bundle args = new Bundle();
        args.putInt(Constants.SELECTED_MANGA_ID, mangaID);
        itMangaDetail.putExtra("param", args);
        startActivity(itMangaDetail);
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
