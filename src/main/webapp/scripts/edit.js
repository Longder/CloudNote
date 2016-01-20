/**
 * 编辑页面的js
 * Created by Longder on 2016/1/11.
 */
//登录验证
var userId = getCookie("userId");
if (userId == null) {
    window.location.href = "log_in.html";
}
var um;
$(function () {
    //实例化Ueditor编辑器
    um = UM.getEditor('myEditor');
    //页面加载完毕，发送ajax请求,加载某个用户下的笔记本列表
    loadNotebooks();
    //添加笔记本按钮点击事件，弹出添加页面
    $("#add_notebook").click(function () {
        $(".opacity_bg").show();
        $("#can").load("alert/alert_notebook.html");
    });
    //添加笔记按钮点击事件
    $("#add_note").click(function () {
        //检测是否有选中的笔记本
        var bookId = $("#book_list li a.checked").parent().data("bookId");
        if (bookId == undefined) {
            alert("请先选中笔记本");
            return;
        }
        $(".opacity_bg").show();
        $("#can").load("alert/alert_note.html");
    });
    //添加笔记本对话框中取消和关闭按钮点击事件
    $("#can").on("click", ".cancle,.close", function () {
        $(".opacity_bg").hide();
        $("#can").empty();
    });
    //点击笔记列表下拉框中的删除按钮，弹出删除界面
    $("#note_list").on("click", ".btn_delete", function () {
        $(".opacity_bg").show();
        $("#can").load("alert/alert_delete_note.html");
    });
    //点击移动按钮，弹出移动界面
    $("#note_list").on("click", ".btn_move", function () {
        $(".opacity_bg").show();
        $("#can").load("alert/alert_move.html", loadToMovePage);
    });
    //绑定点击笔记本加载笔记的事件
    $("#book_list").on("click", "li", loadNotes);
    //点击笔记显示笔记内容的事件
    $("#note_list").on("click", "li", getNote);
    //创建笔记本按钮点击事件,添加笔记本
    $("#can").on("click", "#saveNotebook", saveNotebook);
    //创建笔记按钮的点击事件，添加笔记
    $("#can").on("click", "#saveNote", saveNote);
    //保存笔记按钮的点击事件，更新笔记
    $("#save_note").click(updateNote);
    //点击确定删除按钮，开始删除笔记本
    $("#can").on("click", "#deleteNote", deleteNote);
    //点击笔记列表下拉框中的分享按钮，分享笔记
    $("#note_list").on("click", ".btn_share", shareNote);
    //点击移动界面确定按钮，移动笔记
    $("#can").on("click", "#move_note", moveNote);
    //搜索分享笔记
    $("#search_note").keydown(function (event) {
        if (event.keyCode == 13) {
            //切换显示列表
            changeDiv("#pc_part_6");
            searchShareNote();
        }
    });
    //点击分享笔记预览笔记内容
    $("#search_list").on("click", "li", loadShareNote);
    /**
     * 以下4个方法用来处理笔记列表中的弹出下拉菜单
     */
    $("#note_list").on("mouseenter", "button.btn_slide_down", function () {
        //判断是否是选中的笔记
        if ($(this).parent().is(".checked")) {
            $(this).parent().next().show();
        }
    });
    $("#note_list").on("click", "button.btn_slide_down", function () {
        $(this).parent().next().show();
    });
    $("#note_list").on("mouseleave", "a", function () {
        $(this).next().hide();
    });
    $("#note_list").on("mouseleave", ".note_menu", function () {
        $(this).hide();
    });
    $("#note_list").on("mouseover", ".note_menu", function () {
        $(this).show();
    });
});
//存储所有div面板id的数组
var divArray = ["#pc_part_2", "#pc_part_4", "#pc_part_6", "#pc_part_7", "#pc_part_8"];
//切换笔记页面div
function changeDiv(divId) {
    $(divId).show();
    //除查看自己的笔记外，其他的笔记都是只读的
    if (divId == "#pc_part_2") {
        $("#pc_part_3").show();
        $("#pc_part_5").hide();
    } else {
        $("#pc_part_5").show();
        $("#pc_part_3").hide();
    }
    for (var i = 0; i < divArray.length; i++) {
        if (divId == divArray[i]) {
            continue;
        }
        $(divArray[i]).hide();
    }
}
//拼接一个笔记本li
function createNotebookLi(bookId, bookName) {
    var sli = "<li class='online'>";
    sli += "<a>";
    sli += "<i class='fa fa-book' title='online' rel='tooltip-bottom'></i>";
    sli += bookName;
    sli += "</a>";
    sli += "</li>";
    //利用data函数将bookId和sli绑定
    var li = $(sli);
    li.data("bookId", bookId);
    $("#book_list").append(li);
}
//拼接一个笔记li
function createNoteLi(noteId, noteTitle) {
    var sli = "<li class='online'>";
    sli += "<a>";
    sli += "<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i> ";
    sli += noteTitle;
    sli += "<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'>";
    sli += "<i class='fa fa-chevron-down'></i>";
    sli += "</button>";
    sli += "</a>";
    sli += "<div class='note_menu' tabindex='-1'>";
    sli += "<dl>";
    sli += "<dt>";
    sli += "<button type='button' class='btn btn-default btn-xs btn_move'title='移动至...'>";
    sli += "<i class='fa fa-random'></i>";
    sli += "</button>";
    sli += "</dt>";
    sli += "<dt>";
    sli += "<button type='button' class='btn btn-default btn-xs btn_share' title='分享'>";
    sli += "<i class='fa fa-sitemap'></i>";
    sli += "</button>";
    sli += "</dt>";
    sli += "<dt>";
    sli += "<button type='button' class='btn btn-default btn-xs btn_delete' title='删除'>"
    sli += "<i class='fa fa-times'></i>";
    sli += "</button>";
    sli += "</dt>";
    sli += "</dl>";
    sli += "</div>";
    sli += "</li>";
    var li = $(sli);
    li.data("noteId", noteId);
    $("#note_list").append(li);
}
//加载笔记本列表
function loadNotebooks() {
    $.ajax({
        url: "notebook/load.do",
        type: "post",
        data: {"userId": userId},
        dataType: "json",
        success: function (result) {
            if (result.status == 0) {
                var books = result.data;//笔记本的json对象数组
                $("#book_list").empty();
                for (var i = 0; i < books.length; i++) {
                    var bookId = books[i].id;
                    var bookName = books[i].name;
                    createNotebookLi(bookId, bookName);
                }
            }
        }, error: function () {
            alert("加载笔记本列表失败");
        }
    });
}
//加载笔记列表
function loadNotes() {
    changeDiv("#pc_part_2");
    $("#note_list").empty();
    $("#book_list li a").removeClass("checked");
    $(this).find("a").addClass("checked");
    var bookId = $(this).data("bookId");
    $.ajax({
        url: "note/load.do",
        type: "post",
        data: {"bookId": bookId},
        dataType: "json",
        success: function (result) {
            if (result.status == 0) {
                var notes = result.data;
                for (var i = 0; i < notes.length; i++) {
                    createNoteLi(notes[i].id, notes[i].title);
                }
            }
        }, error: function () {
            alert("加载笔记列表失败");
        }
    });
}
//显示笔记内容
function getNote() {
    $("#note_list li a").removeClass("checked");
    $(this).find("a").addClass("checked");
    var noteId = $(this).data("noteId");
    //发送ajax请求，查询笔记详细信息
    $.ajax({
        url: "note/getNote.do",
        type: "post",
        dataType: "json",
        data: {"noteId": noteId},
        success: function (result) {
            if (result.status == 0) {
                var note = result.data;
                $("#input_note_title").val(note.title);
                um.setContent(note.body);
            }
        }, error: function () {
            alert("加载笔记本详情失败");
        }
    });
}
//添加笔记本
function saveNotebook() {
    //获取笔记本名字
    var bookName = $("#input_notebook").val();
    $.ajax({
        url: "notebook/add.do",
        type: "post",
        dataType: "json",
        data: {"bookName": bookName, "userId": userId},
        success: function (result) {
            if (result.status == 0) {
                var bookId = result.data;
                //创建笔记本li
                createNotebookLi(bookId, bookName);
                //触发关闭对话框事件
                $("#can .close").click();
            }
        }, error: function () {
            alert("添加笔记本失败");
        }
    });
}
//添加笔记
function saveNote() {
    //获取bookId,noteTitle,userId
    var bookId = $("#book_list li a.checked").parent().data("bookId");
    var noteTitle = $("#input_note").val();
    $.ajax({
        url: "note/add.do",
        type: "post",
        dataType: "json",
        data: {"bookId": bookId, "noteTitle": noteTitle, "userId": userId},
        success: function (result) {
            if (result.status == 0) {
                var noteId = result.data;
                createNoteLi(noteId, noteTitle);
                alert("添加笔记本成功");
                //触发关闭对话框事件
                $("#can .close").click();
            }
        }, error: function () {
            alert("创建笔记失败");
        }
    });
}
//更新笔记
function updateNote() {
    var a = $("#note_list li a.checked");
    //判断是否选中笔记
    var noteId = a.parent().data("noteId");
    if (noteId == undefined) {
        alert("请先选择笔记本");
        return;
    }
    var noteBody = um.getContent();
    var noteTitle = $("#input_note_title").val();
    $.ajax({
        url: "note/update.do",
        type: "post",
        dataType: "json",
        data: {"noteId": noteId, "noteBody": noteBody, "noteTitle": noteTitle},
        success: function (result) {
            if (result.status == 0) {
                alert("更新笔记本成功");
                var sa = "<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i> ";
                sa += noteTitle;
                sa += "<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'>";
                sa += "<i class='fa fa-chevron-down'></i>";
                sa += "</button>";
                a.html(sa);
            }
        }, error: function () {
            alert("更新笔记本失败");
        }
    });
}
//删除笔记
function deleteNote() {
    //获取noteId
    var noteId = $("#note_list li a.checked").parent().data("noteId");
    console.log("noteId  " + noteId);
    $.ajax({
        url: "note/delete.do",
        type: "post",
        dataType: "json",
        data: {"noteId": noteId},
        success: function (result) {
            if (result.status == 0) {
                alert("删除成功");
                $("#note_list li a.checked").parent().empty();
                $("#input_note_title").val("");
                um.setContent("");
                //触发关闭对话框事件
                $("#can .close").click();
            }
        }, error: function () {
            alert("删除笔记本失败！");
        }
    });
}
//分享笔记
function shareNote() {
    //获取笔记id
    var noteId = $(this).parents("li").data("noteId");
    $.ajax({
        url: "note/share.do",
        type: "post",
        dataType: "json",
        data: {"noteId": noteId},
        success: function (result) {
            alert(result.message);
        }, error: function () {
            alert("分享笔记失败");
        }
    });
}
//加载笔记本到移动页面中
function loadToMovePage() {
    //获取笔记本信息填充下拉单选项
    var booklis = $("#book_list li");
    for (var i = 0; i < booklis.length; i++) {
        var li = $(booklis[i]);
        var bookName = li.text().trim();
        var bookId = li.data("bookId");
        var option = "<option value='" + bookId + "'>" + bookName + "</option>";
        $("#moveSelect").append(option);
    }
}
//移动笔记
function moveNote() {
    //获取选中的笔记本ID
    var bookId = $("#moveSelect").val();
    if (bookId == "none") {
        alert("请选择笔记本");
        return;
    }
    var li = $("#note_list li a.checked").parent();
    var noteId = li.data("noteId");
    $.ajax({
        url: "note/move.do",
        type: "post",
        dataType: "json",
        data: {"noteId": noteId, "bookId": bookId},
        success: function (result) {
            if (result.status == 0) {
                $("#can .close").click();
                li.remove();
                alert("移动笔记成功");
            }
        }, error: function () {
            alert("移动笔记本失败！！");
        }
    });
}
//搜索分享笔记
function searchShareNote() {
    var keyword = $("#search_note").val();
    $.ajax({
        url: "note/search.do",
        type: "post",
        dataType: "json",
        data: {"keyword": keyword},
        success: function (result) {
            if (result.status == 0) {
                var notes = result.data;
                //循环生成li
                for (var i = 0; i < notes.length; i++) {
                    var title = notes[i].title;
                    var id = notes[i].id;
                    var sli = "<li class='online'>";
                    sli += "<a>";
                    sli += "<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i> ";
                    sli += title;
                    sli += "<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'>";
                    sli += "<i class='fa fa-star'></i>";
                    sli += "</button>";
                    sli += "</a>";
                    sli += "</li>";
                    var li = $(sli);
                    li.data("shareId", id);
                    $("#search_list").append(li);
                }
            }
        }, error: function () {
            alert("搜索笔记失败");
        }
    });
}
//预览分享笔记
function loadShareNote() {
    var shareId = $(this).data("shareId");
    $.ajax({
        url: "note/loadShare.do",
        type: "post",
        dataType: "json",
        data: {"shareId": shareId},
        success: function (result) {
            if (result.status == 0) {
                var title = result.data.title;
                var body = result.data.body;
                $("#noput_note_title").html(title);
                $("#noput_note_body").html(body);
            }
        }, error: function () {
            alert("预览分享笔记失败");
        }
    });
}