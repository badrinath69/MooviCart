import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import com.example.moovicart.R

class MultiSelectedSpinnerAdapter(context: Context, private val items: List<String>) :
    ArrayAdapter<String>(context, 0, items) {

    private val selectedItems = mutableSetOf<String>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_multi_selected_spinner, parent, false)

        val item = getItem(position) ?: return view
        val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
        checkBox.text = item
        checkBox.isChecked = selectedItems.contains(item)

        checkBox.setOnClickListener {
            if (checkBox.isChecked) {
                selectedItems.add(item)
            } else {
                selectedItems.remove(item)
            }
        }

        return view
    }

    fun getSelectedItems(): Set<String> {
        return selectedItems
    }
}
