package com.deathsdoor.ui_core.extras.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.deathsdoor.ui_core.databinding.ItemSingleChoiceBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SingleChoiceBottomSheet: BottomSheetDialogFragment() {
    companion object{
        const val TAG = "SingleChoiceBottomSheet"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val IMGS = "images"
        const val TEXTS = "texts"
    }
    private lateinit var _binding : ItemSingleChoiceBottomSheetBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            //Full screen
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { bottomSheet ->
                val behaviour = BottomSheetBehavior.from(bottomSheet)
                val layoutParams = bottomSheet.layoutParams
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
                bottomSheet.layoutParams = layoutParams
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ItemSingleChoiceBottomSheetBinding.inflate(inflater,container,false)
        return _binding.root
    }

}