function getConnection() {
    return {
	"host": $("#host").val(),
	"port": $("#port").val()
    }
}	

function listBuckets(data, status, xhr) {
    $("#bucket_pane > ul").empty();
    $("#key_pane > ul").empty();
    $("#value_content").empty();
    
    for (var i=0; i<data.length; i++) {
	var elem = $("<li>" + data[i] + "</li>");
	elem.click(clickBucket);
	$("#bucket_pane > ul").append(elem);
    }
}

function clickBucket(event) {
    $("#bucket_pane > ul > li").removeClass("selected-elem");
    $("#key_pane > ul").empty();
    $("#value_content").empty();
    $(event.target).addClass("selected-elem");
    var data = getConnection();
    data["bucket"] = $(event.target).text();

    $.getJSON("/list-keys", data,
	      function(data, status, xhr) {
		  for (var i=0; i<data.length; i++) {
		      var elem = $("<li>" + data[i] + "</li>");
		      elem.click(clickKey);
		      $("#key_pane > ul").append(elem);
		  }
	      });
}

function clickKey(event) {
    $("#key_pane > ul > li").removeClass("selected-elem");
    $("#value_content").empty();
    $(event.target).addClass("selected-elem");

    var toSend = getConnection();
    toSend["bucket"] = $("#bucket_pane > ul > .selected-elem").text();
    toSend["key"] = $(event.target).text();

    $.getJSON("/show-value",
	      toSend,
	      function(data, status, xhr) {
		  $("#value_content").text(data["value"]);
	      });
}

function ready_page() {
    $("#connect").click(
	function (event) {
	    $.getJSON("/list-buckets", getConnection(),	listBuckets);
	}
    )
}

$(ready_page);