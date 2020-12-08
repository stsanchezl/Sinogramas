package gui.sinogramas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import data.sinogramas.QueueDynamicArrayGeneric;
import data.sinogramas.Unihan;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private QueueDynamicArrayGeneric<Unihan> allData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(QueueDynamicArrayGeneric<Unihan> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.allData = itemList;
    }

    @Override
    public int getItemCount() { return allData.length(); }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_sinogram_element, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(allData.getItem(position));
    }

    public void setItems(QueueDynamicArrayGeneric<Unihan> items) {allData = items;}

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

        void bindData(final Unihan item) {
            if (item!=null) {
                sinogram.setText(item.getSinogram());
                radical.setText("Radical: " + item.getRadix());
                meaning.setText("Significado: " + item.getFirstSpanishDefinition());
                pronunciation.setText("Nombre: " + item.getPinyin());
                strokes.setText("Trazos: " + String.valueOf(item.getNumOfStrokes()));
            }
        }
    }
}
