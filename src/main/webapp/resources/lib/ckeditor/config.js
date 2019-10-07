CKEDITOR.editorConfig = function( config ) {
   config.language = 'ko';
   config.uiColor = '#ffe2ed';
   config.skin = 'moonocolor';
   config.resize_dir = 'both';
   config.height = 300;
   config.toolbarCanCollapse = true; 
   config.filebrowserUploadUrl = '/imageUpload';
   config.extraPlugins = 'youtube';
   config.youtube_responsive = true;
   config.youtube_related = true;
   config.youtube_autoplay = false;
   config.youtube_older = false;
   config.youtube_privacy = false;
   config.toolbar = 
   	[
         { name: 'document', items: [ 'Source', '-', 'Save', 'NewPage', 'Preview', 'Print', '-', 'Templates' ] },
         { name: 'clipboard', items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
         { name: 'editing', items: [ 'Find', 'Replace', '-', 'SelectAll', '-', 'Scayt' ] },
         { name: 'forms', items: [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ] },
         '/',
         { name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'CopyFormatting', 'RemoveFormat' ] },
         { name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl', 'Language' ] },
         { name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
         { name: 'insert', items: [ 'Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe' ,'Youtube' ] },
         '/',
         { name: 'styles', items: [ 'Styles', 'Format', 'Font', 'FontSize' ] },
         { name: 'colors', items: [ 'TextColor', 'BGColor' ] },
         { name: 'tools', items: [ 'Maximize', 'ShowBlocks' ] },
         { name: 'about', items: [ 'About'] }
   ];
    
//    config.toolbarGroups = [ { name: 'document',  groups: [ 'mode' ] }, 
//                             { name: 'clipboard', groups: [ 'clipboard', 'undo' ] }, 
//                             { name: 'editing',   groups: [ 'find', 'selection', 'spellchecker' ] },
//                             '/',
//                             { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
//                             { name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
//                             { name: 'links',     groups: ['links',  'insert']}, 
//                             { name: 'tools' }, 
//                             { name: 'colors' },
//                             { name: 'about' }      
//   ];
};

/*
//이미지업로드
@RequestMapping(value="/ckeditor/ImageUpload", method=RequestMethod.POST)
public void ckeditorImageUpload(NoticeBean nBean, final HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) {

  HashMap<String,Object> map = new HashMap<String, Object>();

  response.setCharacterEncoding("UTF-8");
  response.setContentType("text/html; charset-utf-8");

  OutputStream out = null;
  PrintWriter printWriter = null;

  //리턴해줄 변수값(이미지의 url경로)
  String imageURL = null;
  String imageName = null;

  try{
     String fileName = upload.getOriginalFilename(); //첨부파일 이름
     UUID uuid = UUID.randomUUID();
     int index = fileName.lastIndexOf(".");
     String extension = fileName.substring(index + 1, fileName.length());
     byte[] bytes = upload.getBytes(); //첨부파일을 바이트 배열로 저장

     String path = request.getSession().getServletContext().getRealPath("/");
     //System.out.println("★path: " + path);
                    //업로드 할 디렉토리 경로 + fileName; //실제저장되는 곳
     String uploadPath = path +"resources/upload/" + uuid.toString() + "." + extension;
     out = new FileOutputStream(new File(uploadPath));
     out.write(bytes);   //서버 업로드

     String callback = request.getParameter("CKEditorFuncNum");
     printWriter = response.getWriter();

     //URL상의 이미지경로
     String fileUrl = "http://gdj3.gudi.kr/kkla/resources/upload/" + uuid.toString() + "." + extension;

     imageURL = fileUrl;
     imageName = fileName;


     //업로드시 띄워줄 alert
     printWriter.println("<script>window.parent.CKEDITOR.tools.callFunction("
           + callback
           + ", '"
           + fileUrl
           + " ', '업로드 완료'"
           +"); </script>");

        printWriter.flush();
     }catch(IOException e){
        e.printStackTrace();
     }finally{
        try{
           if(out != null) out.close();
           if(printWriter != null) printWriter.close();
        }catch(IOException e){
           e.printStackTrace();
        }
  }

  map.put("imageURL", imageURL);
  map.put("imageName", imageName);         
}
*/