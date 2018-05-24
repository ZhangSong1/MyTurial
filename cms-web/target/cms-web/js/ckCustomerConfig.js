/**
 * 
 */
CKEDITOR.editorConfig = function( config ) {
    config.toolbarGroups = [
      { name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
      { name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
      { name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
      { name: 'forms', groups: [ 'forms' ] },
      { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
      { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
      { name: 'links', groups: [ 'links' ] },
      { name: 'insert', groups: [ 'insert' ] },
      { name: 'styles', groups: [ 'styles' ] },
      { name: 'colors', groups: [ 'colors' ] },
      { name: 'tools', groups: [ 'tools' ] },
      { name: 'others', groups: [ 'others' ] },
      { name: 'about', groups: [ 'about' ] }
    ];

    config.removeButtons = 'Save,NewPage,Preview,Print,Cut,Copy,Paste,PasteText,PasteFromWord,Redo,Undo,Replace,Find,Scayt,SelectAll,Textarea,TextField,Radio,Checkbox,Form,Select,Button,ImageButton,HiddenField,Italic,Underline,Strike,Subscript,Superscript,RemoveFormat,CopyFormatting,Flash,Table,HorizontalRule,SpecialChar,PageBreak,Iframe,ShowBlocks,About';
   
//字体编辑时的字符集 可以添加常用的中文字符：宋体、楷体、黑体等  
  config.font_names = "Arial;Times New Roman;Verdana";
  config.enterMode = CKEDITOR.ENTER_BR;  
  config.shiftEnterMode = CKEDITOR.ENTER_P;
  config.image_previewText=" ";
  config.filebrowserImageUploadUrl= "img/upload";
  config.removeDialogTabs = 'image:advanced;image:Link';
  config.filebrowserImageBrowseUrl = 'img/browser';
  config.extraPlugins = "simage";
  config.imageUploadURL = "img/upload";
};

