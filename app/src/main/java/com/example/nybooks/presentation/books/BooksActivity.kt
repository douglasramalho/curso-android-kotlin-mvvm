package com.example.nybooks.presentation.books

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.R
import com.example.nybooks.data.repository.BooksApiDataSource
import com.example.nybooks.databinding.ActivityBooksBinding
import com.example.nybooks.presentation.base.BaseActivity
import com.example.nybooks.presentation.details.BookDetailsActivity

class BooksActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar(binding.includeToolbar.toolbarMain, R.string.books_title)

        val viewModel: BooksViewModel = BooksViewModel.ViewModelFactory(BooksApiDataSource())
                .create(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with(binding.recyclerBooks) {
                    layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val intent = BookDetailsActivity.getStartIntent(this@BooksActivity, book.title, book.description)
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                binding.viewFlipperBooks.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageResId ->
                    binding.textViewError.text = getString(errorMessageResId)
                }
            }
        })

        viewModel.getBooks()
    }
}
