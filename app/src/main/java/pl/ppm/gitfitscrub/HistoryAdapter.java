package pl.ppm.gitfitscrub;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private ArrayList<ItemTemplate> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public ImageView mImageViewDelete;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public TextView mTextView5;

        public HistoryViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mImageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            mTextView1 = itemView.findViewById(R.id.textViewDate);
            mTextView2 = itemView.findViewById(R.id.textViewTimeValue);
            mTextView3 = itemView.findViewById(R.id.textViewDistanceValue);
            mTextView4 = itemView.findViewById(R.id.textViewAvgSpeedValue);
            mTextView5 = itemView.findViewById(R.id.textViewMaxSpeedValue);

            mImageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public HistoryAdapter(ArrayList<ItemTemplate> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent,
                false);
        HistoryViewHolder evh = new HistoryViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        ItemTemplate currentItem = mExampleList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mImageViewDelete.setImageResource(currentItem.getImageResourceDelete());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
        holder.mTextView3.setText(currentItem.getText3());
        holder.mTextView4.setText(currentItem.getText4());
        holder.mTextView5.setText(currentItem.getText5());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
