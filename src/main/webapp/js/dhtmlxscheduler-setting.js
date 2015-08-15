//settings
scheduler.config.xml_date="%Y-%m-%d %H:%i";
scheduler.config.time_step = 30;
scheduler.config.multi_day = true;
scheduler.locale.labels.section_subject = "Subject";
scheduler.config.first_hour = 6;
scheduler.config.limit_time_select = true;
scheduler.config.details_on_dblclick = true;
scheduler.config.details_on_create = true;

scheduler.templates.event_class=function(start, end, event){
	var css = "";
	if(event.subject) // if event has subject property then special class should be assigned
		css += "event_"+event.subject;
	if(event.id == scheduler.getState().select_id){
		css += " selected";
	}
	return css; // default return
};

var subject = [
	{ key: '', label: 'Appointment' },
	{ key: 'english', label: 'English' },
	{ key: 'math', label: 'Math' },
	{ key: 'science', label: 'Science' }
];

scheduler.config.lightbox.sections=[
	{name:"description", height:43, map_to:"text", type:"textarea" , focus:true},
	{name:"subject", height:20, type:"select", options: subject, map_to:"subject" },
	{name:"time", height:72, type:"time", map_to:"auto" }
];
