package org.zkoss.zkdatahandlerdemo;

import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkdatahandlerdemo.bean.SchedulerEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@NotifyCommands({
    @NotifyCommand(value = "dhtmlxscheduler$addClientEvent", onChange = "_vm_.addEventList"),
    @NotifyCommand(value = "dhtmlxscheduler$deleteClientEvent", onChange = "_vm_.removeEventList")
})
@ToClientCommand({"dhtmlxscheduler$addClientEvent", "dhtmlxscheduler$deleteClientEvent"})
@ToServerCommand({"dhtmlxscheduler$addServerEvent", "dhtmlxscheduler$deleteServerEvent", "dhtmlxscheduler$changeServerEvent"})

public class DhtmlxSchedulerVM {
    private List<SchedulerEvent> eventList;
    private List<SchedulerEvent> addEventList;
    private List<String> removeEventList;
    public List<String> getRemoveEventList() {
        return removeEventList;
    }
    public List<SchedulerEvent> getAddEventList() {
        return addEventList;
    }

    private SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private int _idCnt;
    private Calendar now = Calendar.getInstance();
    @Init
    public void init() {
        _idCnt = 0;
        int m = now.get(Calendar.MONTH) + 1;
        String[][] evts = {{"" + ++_idCnt, "2015-0" + m + "-18 09:00", "2015-0" + m + "-18 12:00", "English lesson", "english"},
                {"" + ++_idCnt, "2015-0" + m + "-20 10:00", "2015-0" + m + "-21 16:00", "Math exam", "math"},
                {"" + ++_idCnt, "2015-0" + m + "-21 10:00", "2015-0" + m + "-21 14:00", "Science lesson", "science"},
                {"" + ++_idCnt, "2015-0" + m + "-23 16:00", "2015-0" + m + "-23 17:00", "English lesson", "english"},
                {"" + ++_idCnt, "2015-0" + m + "-24 09:00", "2015-0" + m + "-24 17:00", "Usual event", ""}
        };

        eventList = new ArrayList<SchedulerEvent>();
        for (String[] evt : evts) {
            try {
                eventList.add(new SchedulerEvent(evt[0], _sdf.parse(evt[1]), _sdf.parse(evt[2]), evt[3], evt[4]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        addEventList = new ArrayList<SchedulerEvent>(eventList);
        removeEventList = new ArrayList<String>();
    }

    @Command("dhtmlxscheduler$addServerEvent")
    public void add(@BindingParam("id") String id, @BindingParam("start_date") String start_date, @BindingParam("end_date") String end_date, @BindingParam("subject") String subject, @BindingParam("text") String text) {
        try {
            eventList.add(new SchedulerEvent(id, _sdf.parse(start_date), _sdf.parse(end_date), subject, text));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Command("dhtmlxscheduler$deleteServerEvent")
    public void delete(@BindingParam("id") String id) {
        for (SchedulerEvent evt : eventList) {
            if (evt.getId().equals(id)) {
                eventList.remove(evt);
                break;
            }
        }
    }

    @Command("dhtmlxscheduler$changeServerEvent")
    public void change(@BindingParam("id") int id, @BindingParam("start_date") String start_date, @BindingParam("end_date") String end_date, @BindingParam("subject") String subject, @BindingParam("text") String text) {
        for (SchedulerEvent evt : eventList) {
            if (evt.getId().equals(id)) {
                try {
                    evt.setStart_date(_sdf.parse(start_date));
                    evt.setEnd_date(_sdf.parse(end_date));
                    evt.setSubject(subject);
                    evt.setText(text);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Command
    @NotifyChange("addEventList")
    public void addEvents() {
        addEventList.clear();
        int m = now.get(Calendar.MONTH) + 1;
        try {
            addEventList.add(new SchedulerEvent(++_idCnt + "", _sdf.parse("2015-0" + m + "-24 09:00"), _sdf.parse("2015-0" + m + "-24 17:00"), "New event1"));
            addEventList.add(new SchedulerEvent(++_idCnt + "", _sdf.parse("2015-0" + m + "-25 09:00"), _sdf.parse("2015-0" + m + "-25 17:00"), "New event2"));
            addEventList.add(new SchedulerEvent(++_idCnt + "", _sdf.parse("2015-0" + m + "-26 09:00"), _sdf.parse("2015-0" + m + "-26 17:00"), "New event3"));
        } catch (ParseException e) {
        }
        eventList.addAll(addEventList);
    }

    @Command
    @NotifyChange("removeEventList")
    public void removeEvents() {
        removeEventList.clear();
        if (eventList.size() < 3) {
            Clients.showNotification("The number of events is less than 3!");
            return;
        }
        for (int i = 0; i <= 3; i++) {
            removeEventList.add(eventList.get(i).getId());
            eventList.remove(i);
        }
    }
}
