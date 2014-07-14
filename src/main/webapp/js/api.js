$.ajax({
    type: "GET",
    url: "http://localhost:8080/news-management/api/all",
    success: function (data) {
        $.each(data, function (key, value) {
            var article = $("#article-block").clone();
            $(article).attr("id", function(i, val){
                return val + value["id"];
            });
            $(article).find("#article-title").text(value["title"]);
            changeId(article, "#article-title", value["id"]);
            $(article).find("#article-date").text(new Date(value["date"]));
            changeId(article, "#article-date", value["id"]);
            $(article).find("#article-description").text(value["description"]);
            changeId(article, "#article-description", value["id"]);
            $("#news-body").append(article);
        });
    },
    error: function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.status + " Error has occurred");
    },
    dataType: "json"
});

function changeId(article, id, value) {
    $(article).find(id).attr("id", function(i, val){
        return val + value;
    });
}