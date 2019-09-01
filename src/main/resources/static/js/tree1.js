function createNode(){
  var root = {
    "id" : "0",
    "text" : "root",
    "value" : "86",
    "showcheck" : true,
    "complete" : true,
    "isexpand" : true,
    "checkstate" : 0,
    "hasChildren" : true
  };
  var arr = [];
  for(var i= 1;i<10; i++){
    var subarr = [];
    for(var j=1;j<10;j++){
      var value = "node-" + i + "-" + j;
      subarr.push( {
         "id" : value,
         "text" : value,
         "value" : value,
         "showcheck" : true,
         "complete" : true,
         "isexpand" : false,
         "checkstate" : 0,
         "hasChildren" : false
      });
    }
    arr.push( {
      "id" : "node-" + i,
      "text" : "node-" + i,
      "value" : "node-" + i,
      "showcheck" : true,
      "complete" : true,
      "isexpand" : false,
      "checkstate" : 0,
      "hasChildren" : true,
      "ChildNodes" : subarr
    });
  }
  root["ChildNodes"] = arr;
  console.log(root)
  return root; 
}

function createNoesAjax() {
    var result = null;
    $.ajax({
        type: "GET",
        async: false,
        url: '/syspermission/getTree/',
        data: null,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            result = data;
        },
        error: function () {
            result = null;
        }
    });
    console.log(result)
    return result;
}

treedata = [createNoesAjax()];
