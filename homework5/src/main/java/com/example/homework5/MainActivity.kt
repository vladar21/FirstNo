package com.example.homework5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private var data: MutableList<Recipe> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        for(i: Int in 1..10) {
//            data.add(Recipe("Recipe $i ${LoremIpsum(1).toString()}", LoremIpsum(100).toString()))
//        }
        data = mutableListOf(
            Recipe("Рецепт 1", "FJDSFJ:SDFJ:SDLFKJ:SDLFJS:FLJS:FJS:DFSFS:F"),
            Recipe("Рецепт 2", "Aenean placerat. In vulputate urna eu arcu. Aliquam erat volutpat. Suspendisse potenti. Morbi mattis felis at nunc. Duis viverra diam non justo. In nisl. Nullam sit amet magna in magna gravida vehicula."),
            Recipe("Рецепт 3", "Nullam eget nisl. Donec vitae arcu. Nam quis nulla. Integer malesuada. In in enim a arcu imperdiet malesuada. Sed vel lectus. Donec odio urna, tempus molestie, porttitor"),
            Recipe("Рецепт 4", "Mauris tincidunt sem sed arcu. Nunc posuere. Nullam lectus justo, vulputate eget, mollis sed, tempor sed, magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus."),
            Recipe("Рецепт 5", "Etiam neque. Curabitur ligula sapien, pulvinar a, vestibulum quis, facilisis vel, sapien."),
            Recipe("Рецепт 6", "Morbi a metus. Phasellus enim erat, vestibulum vel, aliquam a, posuere eu, velit. Nullam sapien sem, ornare ac, nonummy non, lobortis a, enim. Nunc tincidunt"),
            Recipe("Рецепт 7", "Aenean placerat. In vulputate urna eu arcu. Aliquam erat volutpat. Suspendisse potenti. Morbi mattis felis at nunc. Duis viverra diam non justo. In nisl. Nullam sit"),
            Recipe("Рецепт 8", "Maecenas ipsum velit, consectetuer eu, lobortis ut, dictum at, dui. In rutrum. Sed ac dolor sit amet purus malesuada congue. In laoreet, magna id"),
            Recipe("Рецепт 9", "In sem justo, commodo ut, suscipit at, pharetra vitae, orci. Duis sapien nunc, commodo et, interdum suscipit, sollicitudin et, dolor. Pellentesque habitant morbi tristique senectus"),
            Recipe("Рецепт 10", "Nam quis nulla. Integer malesuada. In in enim a arcu imperdiet malesuada. Sed vel lectus. Donec odio urna, tempus molestie, porttitor ut, iaculis"),
            Recipe("Рецепт 11", "Praesent in mauris eu tortor porttitor accumsan. Mauris suscipit, ligula sit amet pharetra semper, nibh ante cursus purus, vel sagittis velit mauris"),
            Recipe("Рецепт 12", "Nam quis nulla. Integer malesuada. In in enim a arcu imperdiet malesuada. Sed vel lectus. Donec odio urna, tempus molestie, porttitor"),
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = RecipeAdapter(data) { recipe ->
            val dialog = AlertDialog.Builder(this)
                .setTitle(recipe.title)
                .setMessage(recipe.fullText)
                .setPositiveButton("OK", null)
                .create()
            dialog.show()
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.notifyDataSetChanged()

    }

    override fun onCreateOptionsMenu (menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate (R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val exitmenu : String = applicationContext.resources.getString(R.string.close_app_choice)
        when (item.itemId){
            R.id.exit -> {
                Toast.makeText(this,exitmenu, Toast.LENGTH_SHORT).show()
                moveTaskToBack(true);
                exitProcess(-1)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}

data class Recipe(val title: String, val fullText: String)

class RecipeAdapter(private val recipes: List<Recipe>, private val listener: (Recipe) -> Unit) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.title.text = recipe.title

        holder.itemView.setOnClickListener {
            listener(recipe)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
    }
}

