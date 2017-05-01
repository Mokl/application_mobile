package be.ecam.ticketing.ticketing_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 01/05/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder>
{
    private ArrayList<Product> Data= new ArrayList<>();
    private ItemAdapterOnClickHandler clickHandler;
    private SQLiteManager local_db;

    public ItemAdapter(ItemAdapterOnClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    public interface ItemAdapterOnClickHandler {
        void onClick(int index);
    }

    public class ItemAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView mTextView;
        public ItemAdapterViewHolder(View view)
        {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.product_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(adapterPosition);
        }
    }

    @Override
    public ItemAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.item_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem,
                viewGroup, shouldAttachToParentImmediately);
        return new ItemAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapterViewHolder holder, int position)
    {
        Product dataForThisItem = Data.get(position);
        holder.mTextView.setText(dataForThisItem.Nom());
    }

    @Override
    public int getItemCount() {
        if (null == Data) return 0;
        return Data.size();
    }

    public void setData(ArrayList<Product> data)
    {
        this.Data =new ArrayList<>();
        this.Data.addAll(data);
    }
}
