package com.henninghall.date_picker;

import com.henninghall.date_picker.wheels.Wheel;

import java.util.ArrayList;
import java.util.Locale;

public class WheelOrderUpdater
{
    private final PickerView pickerView;

    WheelOrderUpdater(final PickerView v) {
        this.pickerView = v;
    }
    
    public void update(final Locale locale, final Mode mode) {
        if (mode != Mode.date) return;

        final ArrayList<Wheel> wheelOrder = this.localeToWheelOrder(locale);
        int afterFirstEmptyWheelIndex = 1;
        pickerView.wheelsWrapper.removeView(wheelOrder.get(0).picker);
        pickerView.wheelsWrapper.removeView(wheelOrder.get(1).picker);
        pickerView.wheelsWrapper.addView(wheelOrder.get(0).picker, afterFirstEmptyWheelIndex);
        pickerView.wheelsWrapper.addView(wheelOrder.get(1).picker, afterFirstEmptyWheelIndex);
    }

    private ArrayList<Wheel> localeToWheelOrder(final Locale locale) {
        final ArrayList<Wheel> wheelList = new ArrayList<Wheel>();
        if (Utils.monthNameBeforeMonthDate(locale)) {
            wheelList.add(this.pickerView.dateWheel);
            wheelList.add(this.pickerView.monthWheel);
        }
        else {
            wheelList.add(this.pickerView.monthWheel);
            wheelList.add(this.pickerView.dateWheel);
        }

        return wheelList;
    }
}

