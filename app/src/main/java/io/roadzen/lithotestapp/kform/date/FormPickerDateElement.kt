package io.roadzen.lithotestapp.kform.date

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import com.thejuki.kformmaster.R
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.listener.OnFormElementValueChangedListener
import com.thejuki.kformmaster.model.FormPickerElement
import java.io.Serializable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Form Picker Date Element
 *
 * Form element for AppCompatEditText (which on click opens a Date dialog)
 *
 * @author **TheJuki** ([GitHub](https://github.com/TheJuki))
 * @version 1.0
 */
class FormPickerDateElement(tag: Int = -1) : FormPickerElement<FormPickerDateElement.DateHolder>(tag) {

    /**
     * Date Format part of DateHolder
     */
    var dateFormat: DateFormat = SimpleDateFormat.getDateInstance()
        set(value) {
            field = value
            this.value = DateHolder(dateValue, dateFormat)
            reInitDialog()
        }

    /**
     * Date Value part of DateHolder
     */
    var dateValue: Date? = null
        set(value) {
            field = value
            this.value = DateHolder(dateValue, dateFormat)
            reInitDialog()
        }

    /**
     * Alert Dialog Builder
     * Used to call reInitDialog without needing context again.
     */
    private var alertDialogBuilder: AlertDialog.Builder? = null

    /**
     * Listener called when a date is set in the Date picker
     * Instantiated during view binding
     */
    internal var dateDialogListener: DatePickerDialog.OnDateSetListener? = null

    override fun clear() {
        this.value?.useCurrentDate()
        (this.editView as? TextView)?.text = ""
        this.valueObservers.forEach { it(this.value, this) }
    }

    /**
     * Date Holder
     *
     * Holds the date fields for [FormPickerDateElement]
     */
    class DateHolder : Serializable {

        var isEmptyDate: Boolean = false
        var dayOfMonth: Int? = null
        var month: Int? = null
        var year: Int? = null
        var dateFormat: DateFormat = SimpleDateFormat.getDateInstance()

        constructor(dayOfMonth: Int, month: Int, year: Int) {
            this.dayOfMonth = dayOfMonth
            this.month = month
            this.year = year
        }

        constructor() {
            useCurrentDate()
        }

        constructor(date: Date?, dateFormat: DateFormat = SimpleDateFormat.getDateInstance()) {
            if (date != null) {
                val calendar = Calendar.getInstance()
                calendar.time = date
                this.year = calendar.get(Calendar.YEAR)
                this.month = calendar.get(Calendar.MONTH) + 1
                this.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            } else {
                isEmptyDate = true
            }

            this.dateFormat = dateFormat
        }

        override fun toString(): String {
            return if (isEmptyDate || year == null || month == null || dayOfMonth == null) ""
            else dateFormat.format(getTime())
        }

        fun validOrCurrentDate() {
            if (year == null || month == null || dayOfMonth == null) {
                useCurrentDate()
            }
        }

        fun useCurrentDate() {
            val calendar = Calendar.getInstance()
            this.year = calendar.get(Calendar.YEAR)
            this.month = calendar.get(Calendar.MONTH) + 1
            this.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            isEmptyDate = true
        }

        fun getTime(): Date? {
            if (isEmptyDate || year == null || month == null || dayOfMonth == null) {
                return null
            }
            val calendar = Calendar.getInstance()
            calendar.set(year ?: 0, if ((month ?: 0) == 0) 0 else (month ?: 0) - 1, dayOfMonth ?: 0)

            return calendar.time
        }

        fun getTime(zone: TimeZone,
                    aLocale: Locale): Date? {
            if (year == null || month == null || dayOfMonth == null) {
                return null
            }
            val calendar = Calendar.getInstance(zone, aLocale)
            calendar.set(year ?: 0, if ((month ?: 0) == 0) 0 else (month ?: 0) - 1, dayOfMonth ?: 0)

            return calendar.time
        }

        fun equals(another: DateHolder): Boolean {
            return another.isEmptyDate == this.isEmptyDate &&
                    another.year == this.year &&
                    another.month == this.month &&
                    another.dayOfMonth == this.dayOfMonth
        }
    }

    override val isValid: Boolean
        get() = !required || (value != null && value?.getTime() != null)

    /**
     * Re-initializes the dialog
     * Should be called value changes by user
     */
    fun reInitDialog(formBuilder: FormBuildHelper? = null) {

        val editTextView = this.editView as? AppCompatEditText

        if (editTextView?.context == null || dateDialogListener == null) {
            return
        }

        val datePickerDialog = DatePickerDialog(editTextView.context,
                dateDialogListener,
                value?.year ?: 0,
                if ((value?.month ?: 0) == 0) 0 else (value?.month ?: 0) - 1,
                value?.dayOfMonth ?: 0)

        if (alertDialogBuilder == null) {
            alertDialogBuilder = AlertDialog.Builder(editTextView.context)
            if (this.confirmTitle == null) {
                this.confirmTitle = editTextView.context.getString(R.string.form_master_confirm_title)
            }
            if (this.confirmMessage == null) {
                this.confirmMessage = editTextView.context.getString(R.string.form_master_confirm_message)
            }
        }

        // display the dialog on click
        val listener = View.OnClickListener {
            if (!confirmEdit || valueAsString.isEmpty()) {
                datePickerDialog.show()
            } else if (confirmEdit && value != null) {
                alertDialogBuilder
                        ?.setTitle(confirmTitle)
                        ?.setMessage(confirmMessage)
                        ?.setPositiveButton(android.R.string.ok) { _, _ ->
                            datePickerDialog.show()
                        }?.setNegativeButton(android.R.string.cancel) { _, _ -> }?.show()
            }
        }

        itemView?.setOnClickListener(listener)
        editTextView.setOnClickListener(listener)
    }
}
