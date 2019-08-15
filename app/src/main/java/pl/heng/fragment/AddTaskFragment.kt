package pl.heng.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.add_task_fragment.*
import pl.heng.R
import pl.heng.databinding.AddTaskFragmentBinding
import pl.heng.viewmodel.AddTaskViewModel
import java.time.LocalDateTime

class AddTaskFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = AddTaskFragment()
    }

    private lateinit var viewModel: AddTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding :AddTaskFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.add_task_fragment,container,false)
        viewModel = ViewModelProviders.of(this).get(AddTaskViewModel(activity?.application!!)::class.java)
        binding.vm = viewModel
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (super.onCreateDialog(savedInstanceState) as BottomSheetDialog).apply {
            setOnShowListener {
                btnNotifyTime.setOnClickListener {
                    notifyHourChange()
                }
            }
            window?.setDimAmount(0.2f)
        }
    }

    private fun notifyHourChange(){
        val picker = TimePickerDialog(this.context,R.style.DialogTheme,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                viewModel.min.set(minute)
                viewModel.hour.set(hourOfDay)
            }, LocalDateTime.now().hour, LocalDateTime.now().minute,true)
        picker.show()
    }


}
