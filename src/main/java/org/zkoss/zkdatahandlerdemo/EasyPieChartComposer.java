package org.zkoss.zkdatahandlerdemo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;

import java.util.LinkedList;
import java.util.List;

public class EasyPieChartComposer extends SelectorComposer<Component>{

    @Wire
    private Div epc;
	public String config = "{barColor: 'rgb(57, 131, 194)', trackColor: '#E2E2E2', scaleColor: false, lineCap: 'butt', lineWidth: zk.parseInt(46/10), animate: 1000}";

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
        epc.setClientDataAttribute("easypiechart", config);
        epc.setClientDataAttribute("size", "100");
        epc.setClientDataAttribute("percent", "33");
	}
    
    @Listen("onClick = #do")
    public void doSth () {
        Clients.sendClientCommand(epc, "easypiechart$clientUpdate", "abc");
    }
}