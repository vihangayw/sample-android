package com.example.sampleapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sampleapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StringAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private List<String> items;
	private OnComponentClickListener listener;
	private Context context;

	public StringAdapter(List<String> items, Context context, OnComponentClickListener listener) {
		if (items == null) items = new ArrayList<>();
		this.items = items;
		this.context = context;
		this.listener = listener;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
				R.layout.list_item_double, parent, false));
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		bindHolder((ViewHolder) holder, position);
	}

	private void bindHolder(ViewHolder holder, int position) {
		String string = this.items.get(position);
		if (string != null) {

		}
	}

	public List<String> getList() {
		return items;
	}

	public void setList(List<String> items) {
		if (items == null) items = new ArrayList<>();
		this.items = items;
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public interface OnComponentClickListener {
		void onComponentClick(View itemView, int position);
	}

	class ViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.txt_primary)
		TextView txtPrimary;
		@BindView(R.id.txt_secondary)
		EditText txtSecondary;
		@BindView(R.id.img_delete)
		View btnDelete;

		ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);

			btnDelete.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (listener != null) listener.onComponentClick(v, getLayoutPosition());
				}
			});
		}
	}
}