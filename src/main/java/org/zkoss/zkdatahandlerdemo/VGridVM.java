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
        users.add(new Person("Claire", "James", "t_t2u9kaz@1mtfbd9tab.com"));
        users.add(new Person("Norman", "Tate", "av4fb.jcs9@uqoj0kt-9jl.com"));
        users.add(new Person("Percy", "Simpson", "ovl9vnsl@mbbrcb1e.com"));
        users.add(new Person("Lana", "Benson", "1-._0ks9h@2hxeugpq13.com"));
        users.add(new Person("Homer", "Singleton", "e_dwaemfy-7w05t@hjoo-v.com"));
        users.add(new Person("Elsa", "Jenkins", "giv@w9lt0p.com"));
        users.add(new Person("Marty", "Ramsey", "auglz5ito@fk6mti.com"));
        users.add(new Person("Jose", "King", "74z._hw0tf80@x88fdh57q.com"));
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
