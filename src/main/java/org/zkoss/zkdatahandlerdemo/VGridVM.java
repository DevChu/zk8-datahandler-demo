package org.zkoss.zkdatahandlerdemo;

import org.zkoss.bind.annotation.*;
import org.zkoss.zkdatahandlerdemo.bean.Person;
import org.zkoss.zul.ListModelList;

import java.util.HashSet;
import java.util.Set;

@NotifyCommand(value = "v-grid$syncClientSelection", onChange = "_vm_.selections")
@ToClientCommand({"v-grid$syncClientSelection"})
@ToServerCommand({"v-grid$syncServerSelection"})
public class VGridVM {
    private ListModelList<Person> users;
    public ListModelList<Person> getUsers() {
        return users;
    }

    private Set<Integer> selections;
    public Set<Integer> getSelections() {
        return selections;
    }

    @Init
    public void init() {
        users = new ListModelList<Person>();
        selections = new HashSet<Integer>();
        users.add(new Person("Kent", "Henry", "nx_7uuloly@a5bat7im.com"));
        users.add(new Person("Timmy", "Lloyd", "vo-o97e_p2o4@lmv667.com"));
        users.add(new Person("Clifton", "Meyer", "uj.vju@qr6huov.com"));
        users.add(new Person("Brandy", "Walsh", "rm8q3w5s0@ec200-erieb.com"));
        selections.add(0);
    }

    @Command("v-grid$syncServerSelection")
    public void syncSelection(@BindingParam("selected") Integer[] selected, @BindingParam("deselected") Integer[] deselected) {
        for (int index : selected) {
            selections.add(index);
        }

        for (int index : deselected) {
            selections.remove(index);
        }
    }

    @Command
    @NotifyChange("selections")
    public void addUser() {
        users.add(new Person("new", "new", "new@new.com"));
    }
}
