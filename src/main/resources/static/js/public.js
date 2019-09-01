function page(index) {
    console.log("index" + index);
    $("#currentPage").val(index);
    console.log($("#currentPage").val());
    $("#fromSubmit").submit()
}