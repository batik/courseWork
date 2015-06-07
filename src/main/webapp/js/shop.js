$('#listPerPage').change(function () {
var params = getQuery();
delete params['page'];
var recordsPerPage = $("#listPerPage").find("option:selected");
params["listPerPage"] = recordsPerPage.val();
var newUrl = window.pageContext + "/product?" + $.param(params);
window.location.href = newUrl;
});

$('.pages a').click(function(e) {
  var params = getQuery();
  params['page'] = $(e.target).text();
  var newUrl = window.pageContext + "/product?" + $.param(params);
  window.location.href = encodeURI(newUrl).replace(/%255B/g, '').replace(/%255D/g, '');;

});

$('#filter input[name=filterButton]').click(function(){
  var params = getQuery();
  params['page'] = '1';
  params['name'] = $('#filter input[name=name]').val();
  params['author'] = $('#filter input[name=author]').val();
  params['startYear'] = $('#filter input[name=startYear]').val();
  params['endYear'] = $('#filter input[name=endYear]').val();
  params['startPrice'] = $('#filter input[name=startPrice]').val();
  params['endPrice'] = $('#filter input[name=endPrice]').val();
  params['sortType'] = $('#filter input[name=sortType]:checked').val();
  delete params['categories'];
  var newUrl = "/product?" + $.param(params);
  $('#filter input[name=categories]:checked').each(function(){
        if(newUrl!="/product?"){
            newUrl = newUrl + '&categories=' + $(this).val();}
        else{
            newUrl = newUrl + 'categories=' + $(this).val();}
  });
  window.location.href = newUrl;
});

$('#filter input[name=cancelFilter]').click(function(){
  var params = getQuery();
    delete params['name'];
    delete params['author'];
    delete params['startYear'];
    delete params['endYear'];
    delete params['startPrice'];
    delete params['endPrice'];
    delete params['categories'];
    delete params['sortType'];
  var newUrl = window.pageContext + "/product?" + $.param(params);
  window.location.href = newUrl;
});

function getQuery(){
var queries = {};
var categories = [];
$.each(document.location.search.substr(1).split('&'), function(c,q){
    var i = q.split('=');
     if ((typeof(i[0]) != 'undefined') && (typeof(i[1]) != 'undefined')) {
        var key = i[0].toString();
        var value = i[1].toString();
        if(key == 'categories'){
            categories.push(value);
            queries[key] = categories;
        }
        else{
            queries[key] = value;
        }
     }
});
return queries;}

function buy(id){
    $.ajax({
    type: "POST",
    url: window.pageContext + "/cart/add",
    data: {"productId": id},
    async: true,
    success: function (data) {
        $("#countCart").html(data.count);
        $("#totalCart").html(data.total);
    }
});
}

$('#locale').change(function () {
var params = getQuery();
var locale = $("#locale").find("option:selected");
params["locale"] = locale.val();
var newUrl = window.pageContext + "?" + $.param(params);
window.location.href = newUrl;
});
