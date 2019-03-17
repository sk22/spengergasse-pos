package at.spengergasse.kai17521.lab09.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import at.spengergasse.kai17521.lab09.R;
import at.spengergasse.kai17521.lab09.model.Wassersport;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
  private List<Wassersport> wassersportList;
  private static final String TAG = "RecyclerViewAdapter";

  public RecyclerViewAdapter(List<Wassersport> wassersportList) {
    this.wassersportList = wassersportList;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
    View v = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.selectable_list_item_icon, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    Wassersport wassersport = wassersportList.get(i);
    viewHolder.text.setText(
      String.format("%S: %s", wassersport.getDisziplin(), wassersport.getKurzbez())
    );
    viewHolder.itemView.setOnClickListener(new ItemClickListener(wassersport));
    switch (wassersport.getSportart()) {
      case "Surfen": setDrawable(viewHolder, R.drawable.surfen); break;
      case "Segeln": setDrawable(viewHolder, R.drawable.segeln); break;
      case "Rudern": setDrawable(viewHolder, R.drawable.rudern); break;
      case "Paddeln": setDrawable(viewHolder, R.drawable.paddeln); break;
    }
  }

  private void setDrawable(ViewHolder viewHolder, int id) {
    viewHolder.icon.setImageDrawable(viewHolder.itemView.getResources().getDrawable(id));
    // viewHolder.text.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0);
  }

  @Override
  public int getItemCount() {
    return wassersportList.size();
  }


  public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView text;
    ImageView icon;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      text = itemView.findViewById(android.R.id.text1);
      icon = itemView.findViewById(android.R.id.icon);
    }
  }

  public static class ItemClickListener implements View.OnClickListener {
    Wassersport wassersport;

    public ItemClickListener(Wassersport wassersport) {
      this.wassersport = wassersport;
    }

    @Override
    public void onClick(View v) {
      Log.d(TAG, "Clicked " + wassersport.getId() + " " + wassersport.getSportart());
      Intent intent = new Intent(v.getContext(), DetailActivity.class);
      intent.putExtra("id", wassersport.getId());
      v.getContext().startActivity(intent);
    }
  }
}
