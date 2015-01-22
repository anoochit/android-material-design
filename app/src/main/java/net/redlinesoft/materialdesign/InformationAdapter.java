package net.redlinesoft.materialdesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.InformationViewHolder> {

    private LayoutInflater inflater;
    
    List<Information> data= Collections.emptyList();
    

    public InformationAdapter(Context context, List<Information> data) {
       inflater=LayoutInflater.from(context);
        this.data=data;
   }
    
    @Override
    public InformationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_row,viewGroup,false);
        InformationViewHolder holder = new InformationViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(InformationViewHolder viewHolder, int i) {
        Information current = data.get(i);
        viewHolder.title.setText(current.title);
        viewHolder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    
    class InformationViewHolder extends RecyclerView.ViewHolder {
        
        ImageView icon;
        TextView title;

        public InformationViewHolder(View itemView) {
            super(itemView);
            
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            title = (TextView) itemView.findViewById(R.id.listTitle);

        }
    }
}
