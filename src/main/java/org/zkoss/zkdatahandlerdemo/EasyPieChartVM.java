package org.zkoss.zkdatahandlerdemo;

import org.zkoss.bind.annotation.*;

@NotifyCommand(value = "easypiechart$clientUpdate", onChange = "_vm_.percent")
@ToClientCommand({"easypiechart$clientUpdate"})
public class EasyPieChartVM {
    private Integer percent;

    public Integer getPercent() {
        return percent;
    }

    @Command
    @NotifyChange("percent")
    public void update() {
        percent = (int) (Math.random() * 100);
    }
}
