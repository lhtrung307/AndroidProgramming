package com.example.listentomusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import retrofit2.Call;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listentomusic.Activity.DanhsachbaihatActivity;
import com.example.listentomusic.Adapter.PlaylistAdapter;
import com.example.listentomusic.Model.Playlist;
import com.example.listentomusic.R;
import com.example.listentomusic.Service.APIService;
import com.example.listentomusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {

    View view;
    ListView lvplaylisst;
    TextView txttitleplaylisst, txtviewxemthemplaylist;
    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> mangplaylist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_playlist, container, false);
         lvplaylisst = view.findViewById(R.id.listviewplaylist);
         txttitleplaylisst = view.findViewById(R.id.textviewtitleplaylist);
         txtviewxemthemplaylist = view.findViewById(R.id.textviewviewmoreplaylist);

        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetPlaylistCurentDay();
        ((retrofit2.Call) callback).enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(retrofit2.Call call, Response response) {
                mangplaylist = (ArrayList<Playlist>) response.body();
                if(response.body() == null){
                    Log.d("","Ko có dữ liệu data");
                    return;
                }
                playlistAdapter = new PlaylistAdapter(getActivity(), android.R.layout.simple_list_item_1, mangplaylist);
                lvplaylisst.setAdapter(playlistAdapter);
                setListViewHeightBasedOnChildren(lvplaylisst);
                lvplaylisst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                        intent.putExtra("itemplaylist", mangplaylist.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable t) {

            }
        });
        }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
