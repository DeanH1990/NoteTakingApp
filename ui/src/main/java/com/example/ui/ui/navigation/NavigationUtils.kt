package com.example.ui.ui.navigation

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun <T: ViewModel> handleNavigation(
    context: Context,
    messageResId: Int,
    navigate: () -> Unit,
    viewModel: T,
    isNoteEmpty: () -> Boolean,
    action: suspend () -> Unit
) {
    viewModel.viewModelScope.launch {
        if (isNoteEmpty()) {
            Toast.makeText(
                context, context.getString(messageResId), Toast.LENGTH_SHORT
            ).show()
        } else {
            action()
        }
        navigate()
    }
}