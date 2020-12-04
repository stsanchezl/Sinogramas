package gui.sinogramas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import data.sinogramas.ListArrayGeneric;
import data.sinogramas.ListDynamicArrayGeneric;
import data.sinogramas.UnorderedListArrayGeneric;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private UnorderedListArrayGeneric<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(UnorderedListArrayGeneric<ListElement> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() { return mData.length(); }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_sinogram_element, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.getItem(position));
    }

    public void setItems(UnorderedListArrayGeneric<ListElement> items) {mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sinogram, radical, meaning, pronunciation, strokes;

        ViewHolder(View itemView){
            super(itemView);
            sinogram = itemView.findViewById(R.id.sinogramText);
            radical = itemView.findViewById(R.id.radicalText);
            meaning = itemView.findViewById(R.id.meaningText);
            pronunciation = itemView.findViewById(R.id.pronunciationText);
            strokes = itemView.findViewById(R.id.strokesText);
        }

        void bindData(final ListElement item) {
            sinogram.setText(item.getSinogram());
            radical.setText("Radical: " + item.getRadical());
            meaning.setText("Significado: " + item.getMeaning());
            pronunciation.setText("Nombre: " + item.getPronunciation());
            strokes.setText("Trazos: " + item.getStrokes());
        }
    }
}
