<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>WeUI</title>
    <link rel="stylesheet" href="node_modules/weui/dist/style/weui.min.css"/>
</head>
<body>
<h1>button组</h1>
Button
按钮可以使用 a 或者 button 标签。wap 上要触发按钮的 active 态，必须触发 ontouchstart 事件，可以在 body 上加上 ontouchstart="" 全局触发。
按钮常见的操作场景：确定、取消、警示，分别对应 class：weui_btn_primary、weui_btn_default、weui_btn_warn，每种场景都有自己的置灰态weui_btn_disabled
，除此外还有一种镂空按钮 weui_btn_plain_xxx，客户端 webview 里的按钮尺寸有两类，默认宽度100%，小型按钮宽度自适应，两边边框与文本间距0.75em：
<a href="#" class="weui_btn weui_btn_primary">按钮</a>
<a href="#" class="weui_btn weui_btn_disabled weui_btn_primary">按钮</a>
<a href="#" class="weui_btn weui_btn_warn">确认</a>
<a href="#" class="weui_btn weui_btn_disabled weui_btn_warn">确认</a>
<a href="#" class="weui_btn weui_btn_default">按钮</a>
<a href="#" class="weui_btn weui_btn_disabled weui_btn_default">按钮</a>
<div class="button_sp_area">
    <a href="#" class="weui_btn weui_btn_plain_default">按钮</a>
    <a href="#" class="weui_btn weui_btn_plain_primary">按钮</a>
    <a href="#" class="weui_btn weui_btn_mini weui_btn_primary">按钮</a>
    <a href="#" class="weui_btn weui_btn_mini weui_btn_default">按钮</a>
</div>
Cell
Cell，列表视图，用于将信息以列表的结构显示在页面上，是 wap 上最常用的内容结构。Cell 由多个 section 组成，每个 section 包括
section header weui_cells_title以及 cells weui_cells。 Cell 由 thumbnail weui_cell_hd、body weui_cell_bd、accessory
weui_cell_ft 三部分组成， cell 采用自适应布局，在需要自适应的部分加上 class weui_cell_primary即可：
<div class="weui_cells_title">带说明的列表项</div>
<div class="weui_cells">
    <div class="weui_cell">
        <div class="weui_cell_bd weui_cell_primary">
            <p>标题文字</p>
        </div>
        <div class="weui_cell_ft">
            说明文字
        </div>
    </div>
</div>
Cell 可根据需要进行各种自定义扩展，包括辅助说明、跳转、单选、复选等。下面以带图标、说明、跳转的列表项，其他情况可以直接参考 example 下的代码：
<div class="weui_cells_title">带图标、说明、跳转的列表项</div>
<div class="weui_cells weui_cells_access">

    <a class="weui_cell" href="#">
        <div class="weui_cell_hd">
            <img src="" alt="icon" style="width:20px;margin-right:5px;display:block">
        </div>
        <div class="weui_cell_bd weui_cell_primary">
            <p>cell standard</p>
        </div>
        <div class="weui_cell_ft">
            说明文字
        </div>
    </a>
    <a class="weui_cell" href="#">
        <div class="weui_cell_hd">
            <img src="" alt="icon" style="width:20px;margin-right:5px;display:block">
        </div>
        <div class="weui_cell_bd weui_cell_primary">
            <p>cell standard</p>
        </div>
        <div class="weui_cell_ft">
            说明文字
        </div>
    </a>
</div>
</body>
</html>