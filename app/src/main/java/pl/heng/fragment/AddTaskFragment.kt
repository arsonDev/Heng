package pl.heng.fragment

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams
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
        val binding :AddTaskFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.add_task_fragment,container,false)
        viewModel = ViewModelProviders.of(this).get(AddTaskViewModel(activity?.application!!)::class.java)
        binding.vm = viewModel
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (super.onCreateDialog(savedInstanceState) as BottomSheetDialog).apply {
            setOnShowListener {
                stepView.setOnSeekChangeListener(object : OnSeekChangeListener {
                    override fun onSeeking(seekParams: SeekParams) {
                        viewModel.countDay.value = seekParams.progress
                    }

                    override fun onStartTrackingTouch(seekBar: IndicatorSeekBar) {}

                    override fun onStopTrackingTouch(seekBar: IndicatorSeekBar) {}
                })

                btnNotifyTime.setOnClickListener {
                    notifyHourChange()
                }
                btnEndDate.setOnClickListener {
                    //todo: dodac koncowa date
                }
            }
            window?.setDimAmount(0.2f)
        }
    }

    private fun notifyHourChange(){
        val picker = TimePickerDialog(this.context, pl.heng.R.style.DialogTheme,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
//                viewModel.notifyTime = "${hourOfDay}:${minute}"
            }, LocalDateTime.now().hour, LocalDateTime.now().minute,true)
        picker.show()
    }
}
