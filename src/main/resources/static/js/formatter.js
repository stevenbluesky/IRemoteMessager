function formatter_platform(value, row, index) {
    if(value==undefined){
        value=row.platform;
    }
    if (value == 999999) {
        return "默认";
    }else if (value == 1) {
        return 1;
    }
    else if (value == 2) {
        return 2;
    }
    else if (value == 3) {
        return 3;
    }
    else if (value == 4) {
        return 4;
    }
    else if (value == 5) {
        return 5;
    }
    else if (value == 6) {
        return 6;
    }
    else if (value == 7) {
        return 7;
    }
    else if (value == 8) {
        return 8;
    }
    else if (value == 9) {
        return 9;
    }
    else if (value == 10) {
        return 10;
    }
}
function formatter_type(value, row, index) {
    if (value == 1) {
        return "附加字段";
    }
    else if (value == 2) {
        return "单句";
    }
    else if (value == 3) {
        return "邮件标题";
    }
    else if (value == 4) {
        return "邮件内容";
    }
}
function formatter_language(value, row, index) {
    if (value == "zh_CN") {
        return "中文(zh_CN)";
    }
    else if (value == "ch_HK") {
        return "繁体(zh_HK)";
    }
    else if (value == "en_US") {
        return "英文(en_US)";
    }
    else if (value == "fr_CA") {
        return "法语(fr_CA)";
    }else if(value == "vi_VN"){
        return "越南语(vi_VN)";
    }
}


var platform = [
    {platformName:'默认',platformValue:'999999'},
    {platformName:'小白管家',platformValue:'93'},
    {platformName:'大白管家',platformValue:'94'},
    {platformName:'中白管家',platformValue:'95'},
    {platformName:'高白管家',platformValue:'96'},
    {platformName:'低白管家',platformValue:'97'}]

var language = [
    {languageName:'中文(zh_CN)',languageValue:'zh_CN'},
    {languageName:'繁体(zh_HK)',languageValue:'ch_HK'},
    {languageName:'英文(en_US)',languageValue:'en_US'},
    {languageName:'法语(fr_CA)',languageValue:'fr_CA'},
    {languageName:'越南语(vi_VN)',languageValue:'vi_VN'}]

var type = [
    {typeName:'推送附加字段',typeValue:'1'},
    {typeName:'单句',typeValue:'2'},
    {typeName:'邮件标题',typeValue:'3'},
    {typeName:'邮件内容',typeValue:'4'}]