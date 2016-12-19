<?php

  $board_id = sanitize_text_field($_GET['id']);

  if($board_id){

    $board_title             = get_the_title($board_id);
    $board_slug              = get_post_meta($board_id, 'kingkongboard_slug', true);
    $board_shortcode         = get_post_meta($board_id, 'kingkongboard_shortcode', true);
    $board_rows              = get_post_meta($board_id, 'kingkongboard_rows', true);
    $board_search            = get_post_meta($board_id, 'kingkongboard_search', true);
    $board_editor            = get_post_meta($board_id, 'kingkongboard_editor', true);
    $board_thumb_dp          = get_post_meta($board_id, 'kingkongboard_thumbnail_dp', true);
    $board_thumb_input       = get_post_meta($board_id, 'kingkongboard_thumbnail_input', true);
    $board_thumb_upload      = get_post_meta($board_id, 'thumbnail_upload', true);
    $board_file_upload       = get_post_meta($board_id, 'file_upload', true);
    $board_captcha           = get_post_meta($board_id, 'board_captcha', true);
    $comment_captcha         = get_post_meta($board_id, 'comment_captcha', true);
    $board_captcha_key       = get_post_meta($board_id, 'board_captcha_key', true);
    $exclude_keyword         = get_post_meta($board_id, 'kkb_exclude_keyword', true);
    $board_reply_use         = get_post_meta($board_id, 'kkb_reply_use', true);
    $board_must_secret       = get_post_meta($board_id, 'kkb_must_secret', true);
    $board_must_thumb        = get_post_meta($board_id, 'kkb_must_thumbnail', true);
    $board_must_section      = get_post_meta($board_id, 'kkb_must_section', true);
    $board_auto_link         = get_post_meta($board_id, 'kkb_auto_link', true);
    $board_comment_auto_link = get_post_meta($board_id, 'kkb_comment_auto_link', true);
    $board_read_under        = get_post_meta($board_id, 'kkb_read_under_loop', true);
    $attach_number           = get_post_meta($board_id, 'kkb_attach_number', true);
    $board_iframe_use        = get_post_meta($board_id, 'kkb_iframe_use', true);
    $comment_html_use        = get_post_meta($board_id, 'kkb_comment_html_use', true);
    $board_language          = get_post_meta($board_id, 'kkb_language', true);

    $captcha_site_key   = null;
    $captcha_secret_key = null;

    if($board_captcha_key){
      $keys = unserialize($board_captcha_key);
      $captcha_site_key   = $keys['site_key'];
      $captcha_secret_key = $keys['secret_key'];
    }

    $thumb_con_include  = "";
    $thumb_con_exclude  = "";

    (!$attach_number || empty($attach_number)) ? $attach_number = 1 : $attach_number = $attach_number;
    ($board_captcha == "T") ? $board_captcha_checked = "checked" : $board_captcha_checked = null;
    ($comment_captcha == "T") ? $comment_captcha_checked = "checked" : $comment_captcha_checked = null;
    ($board_thumb_dp == "display") ? $thumb_dp_checked = "checked" : $thumb_dp_checked = null;
    ($board_must_secret == "T") ? $board_must_secret_checked = "checked" : $board_must_secret_checked = null;
    ($board_must_section == "T") ? $board_must_section_checked = "checked" : $board_must_section_checked = null;
    ($board_must_thumb == "T") ? $board_must_thumb_checked = "checked" : $board_must_thumb_checked = null;
    ($board_auto_link == "T") ? $board_auto_link_selected = "checked" : $board_auto_link_selected = null;
    ($board_comment_auto_link == "T") ? $board_comment_auto_link_checked = "checked" : $board_comment_auto_link_checked = null;
    ($board_read_under == "T") ? $board_read_unser_loop_checked = "checked" : $board_read_unser_loop_checked = null;
    ($board_iframe_use == 'T') ? $board_iframe_use_selected = "checked" : $board_iframe_use_selected = null;
    ($comment_html_use == "T") ? $comment_html_use_checked = "checked" : $comment_html_use_checked = null;

    switch($board_thumb_input){
      case "T" :
        $thumb_con_include = "checked";
      break;

      case "F" :
        $thumb_con_exclude = "checked";
      break;

      default :
        $thumb_con_include = "checked";
      break;
    }

    $kkb_reply_use    = null;
    $kkb_reply_notuse = null;

    switch($board_reply_use){
      case "T" :
        $kkb_reply_use = "checked";
      break;

      case "F" :
        $kkb_reply_notuse = "checked";
      break;

      default :
        $kkb_reply_use = "checked";
      break;
    }

?>
  <div class="head-area">
    <div style="float:left; position:relative; top:10px; margin-right:10px">
      <a href="?page=KingkongBoard"><img src="<?php echo KINGKONGBOARD_PLUGINS_URL;?>/assets/images/logo-kingkongboard.png" style="width:220px; height:auto"></a>
    </div>
    <div style="float:left; font-size:18px; margin-top:14px; margin-left:20px"><?php echo __('게시판 설정 변경', 'kingkongboard');?></div>
    <div style="float:right; position:relative; top:8px">
      <div class="fb-like" data-href="https://facebook.com/superrocketer" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true" style="position:relative; top:-10px; margin-right:10px"></div>
      <a href="http://superrocket.io" target="_blank"><img src="<?php echo KINGKONGBOARD_PLUGINS_URL;?>/assets/images/superrocket-symbol.png" style="height:34px; width:auto" class="superrocket-logo" alt="superrocket.io"></a>
      <a href="https://www.facebook.com/superrocketer" target="_blank"><img src="<?php echo KINGKONGBOARD_PLUGINS_URL;?>/assets/images/icon-facebook.png" style="height:34px; width:auto" class="superrocket-logo" alt="facebook"></a>
      <a href="https://instagram.com/superrocketer/" target="_blank"><img src="<?php echo KINGKONGBOARD_PLUGINS_URL;?>/assets/images/icon-instagram.png" style="height:34px; width:auto" class="superrocket-logo" alt="instagram"></a>
    </div>
  </div>
  <div class="content-area">
    <div style="padding:20px 10px">
      <div style="float:left; position:relative; top:0px; margin-right:10px">"킹콩마트가 오픈하였습니다. 보다 다양한 스킨과 익스텐션을 경험하세요! "</div> <a href="?page=srshop" class="button-kkb kkbred">킹콩마트 둘러보기</a>
    </div>
  </div>
  <div class="notice-toolbox-wrapper"></div>
  <form id="kkb-create-board-form">
    <input type="hidden" name="kkb_type" value="modify">
    <input type="hidden" name="board_id" value="<?php echo $board_id;?>">
    <div class="settings-panel">
      <div class="settings-panel-left">
        <div class="settings-title">Board Settings</div>
        <div class="settings-table-wrapper">
          <table>
            <tr>
              <th><?php echo __('게시판 명:', 'kingkongboard');?></th>
              <td>
                <input type="text" class="kkb-input" name="kkb_board_name" value="<?php echo $board_title;?>"> <span class="kkb-required">*</span>
                <div class="description-container">
                  <span class="description"><?php echo __('보여질 게시판의 이름입니다. 예 : 공지사항', 'kingkongboard');?></span>
                </div>
              </td>
            </tr>
            <tr>
              <th><?php echo __('게시판 슬러그:', 'kingkongboard');?></th>
              <td>
                <input type="text" class="kkb-input" name="kkb_board_slug" value="<?php echo $board_slug;?>" disabled> <span class="kkb-required">*</span>
                <div class="description-container">
                  <span class="description"><?php echo __('게시판 슬러그는 초기 설정값에서 변경할 수 없습니다.', 'kingkongboard');?></span>
                </div>
              </td>
            </tr>
            <tr>
              <th><?php echo __('게시판 숏코드:', 'kingkongboard');?></th>
              <td>
                <input type="text" class="kkb-input" name="kkb_board_shortcode" value="<?php echo $board_shortcode;?>" disabled>
                <div class="description-container">
                  <span class="description"><?php echo __('이 숏코드를 원하시는 Post 나 Page 에 붙여넣으면 노출 됩니다.', 'kingkongboard');?></span>
                </div>
              </td>
            </tr>
            <tr>
              <th><?php echo __('게시물 표시:', 'kingkongboard');?></th>
              <td>
                <input type="text" class="kkb-input" name="kkb_board_rows" value="<?php echo $board_rows;?>">
                <div class="description-container">
                  <span class="description"><?php echo __('한 페이지에 보여지는 게시물 숫자를 의미합니다.', 'kingkongboard');?></span>
                </div>
              </td>
            </tr>
            <tr>
              <th><?php echo __('글 작성 에디터:', 'kingkongboard');?></th>
              <td>
                <select class="kkb-input-select" name="kkb_board_editor">
                  <option value="textarea"><?php echo __('Textarea 사용', 'kingkongboard');?></option>
                  <option value="wp_editor"><?php echo __('WP 내장 에디터 사용', 'kingkongboard');?></option>
<?php

  $path = TEMPLATEPATH."/kingkongboard/editor";
  $dirs = array_filter(glob($path . '/*' , GLOB_ONLYDIR), 'is_dir');
  foreach($dirs as $dir){
    if (file_exists($dir."/kingkongboard-editor.php")) {
      include($dir."/kingkongboard-editor.php");
      echo "<option value='".$Slug."'>".$EditorName."</option>";
    }
  } 

?>
                </select>
                <div class="description-container">
                  <span class="description"><?php echo __('본문 작성 에디터를 설정 합니다.', 'kingkongboard');?></span>
                </div>
              </td>
            </tr>
            <tr>
              <th><?php _e('게시판 언어설정', 'kingkongboard');?></th>
              <td>
                <select class="kkb-input-select" name="kkb_language">
                  <option value="default" <?php if($board_language == 'default') : echo 'selected'; endif; ?>><?php _e('사이트 언어 설정에 따름', 'kingkongboard');?></option>
                  <option value="ko_KR" <?php if($board_language == 'ko_KR') : echo 'selected'; endif; ?>><?php _e('한국어', 'kingkongboard');?></option>
                  <option value="en" <?php if($board_language == 'en') : echo 'selected'; endif; ?>><?php _e('영어', 'kingkongboard');?></option>
                  <option value="zh" <?php if($board_language == 'zh') : echo 'selected'; endif; ?>><?php _e('중국어', 'kingkongboard');?></option>
                  <option value="ja" <?php if($board_language == 'ja') : echo 'selected'; endif; ?>><?php _e('일본어', 'kingkongboard');?></option>
                </select> 
                <div class="description-container">
                  <span class="description"><?php echo __('사이트 언어 설정에 따름을 선택시 설정->일반의 사이트 언어 선택에 따라 변경됩니다.', 'kingkongboard');?></span>
                </div>  
              </td>
            </tr>
            <tr>
              <th><?php echo __('답글쓰기 사용', 'kingkongboard');?></th>
              <td>
                <input type="radio" name="kkb_reply_use" value="T" <?php echo $kkb_reply_use;?>><?php echo __('사용', 'kingkongboard');?> <input type="radio" name="kkb_reply_use" value="F" <?php echo $kkb_reply_notuse;?>><?php echo __('미사용', 'kingkongboard');?>
                <div class="description-container">
                  <span class="description"><?php echo __('답글쓰기 미사용 지정시 답글쓰기 버튼이 노출되지 않습니다. 사용으로 지정 후 권한설정의 쓰기 권한이 없다면 또한 노출 되지 않습니다.', 'kingkongboard');?></span>
                </div>                
              </td>
            </tr>
            <tr>
              <th><?php echo __('조건 설정', 'kingkongboard');?> :</th>
              <td>
                <input type="checkbox" name="kkb_must_section" value="T" <?php echo $board_must_section_checked;?>> <?php _e('분류 반드시 선택', 'kingkongboard');?>
                <div class="description-container" style="margin-bottom:10px">
                  <span class="description"><?php echo __('분류가 있다면 반드시 선택해야만 글이 등록 됩니다.', 'kingkongboard');?></span>
                </div>                
                <input type="checkbox" name="kkb_must_secret" value="T" <?php echo $board_must_secret_checked;?>> <?php echo __('전체 게시글 비밀글로 설정', 'kingkongboard');?>
                <div class="description-container" style="margin-bottom:10px">
                  <span class="description"><?php echo __('비밀글 설정 여부에 상관없이 모든 글들이 비밀글로 등록 됩니다.', 'kingkongboard');?></span>
                </div>
                <input type="checkbox" name="kkb_must_thumbnail" value="T" <?php echo $board_must_thumb_checked;?>> <?php echo __('썸네일 반드시 등록', 'kingkongboard');?>
                <div class="description-container">
                  <span class="description"><?php echo __('썸네일을 반드시 업로드 해야만 글이 등록 됩니다.', 'kingkongboard');?></span>
                </div> 
              </td>
            </tr>
            <tr>
              <th><?php _e('본문 하단 리스트', 'kingkongboard');?> :</th>
              <td>
                <input type="checkbox" name="kkb_read_under_loop" value="T" <?php echo $board_read_unser_loop_checked;?>> <?php _e('현재 페이지 리스트를 표시', 'kingkongboard');?>
                <div class="description-container" style="margin-bottom:10px">
                  <span class="description"><?php echo __('본문하단(글읽기페이지)에 현재 페이지 리스트를 표시 합니다.', 'kingkongboard');?></span>
                </div>                  
              </td>
            </tr>
            <tr>
              <td colspan="2"><hr></td>
            </tr>
            <tr>
              <th><?php echo __('자동글 방지', 'kingkongboard');?></th>
              <td> 
                <table>
                  <tr>
                    <td width="100px">Site Key</td>
                    <td><input type="text" class="kkb-input" name="kkb_board_captcha_sitekey" value="<?php echo $captcha_site_key;?>"></td>
                  </tr>
                  <tr>
                    <td>Secret Key</td>
                    <td><input type="text" class="kkb-input" name="kkb_board_captcha_secretkey" value="<?php echo $captcha_secret_key;?>"></td>
                  </tr>
                  <tr>
                    <td colspan="2">
                      <input type="checkbox" name="kkb_board_captcha" value="T" <?php echo $board_captcha_checked;?>> <?php echo __('자동글 방지(google reCAPTCHA)를 사용합니다.', 'kingkongboard');?>
                    <div class="description-container">
                      <span class="description"><?php echo __('사용하지 않으시려면 해제 하시면 됩니다.', 'kingkongboard');?></span>
                    </div> 
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2">
                      <input type="checkbox" name="kkb_comment_captcha" value="T" <?php echo $comment_captcha_checked;?>> <?php echo __('댓글 자동글 방지(google reCAPTCHA)를 사용합니다.', 'kingkongboard');?>
                    <div class="description-container">
                      <span class="description"><?php _e('사용하지 않으시려면 해제 하시면 됩니다.', 'kingkongboard');?></span>
                    </div>
                  </tr>
                </table>
                <div class="description-container">
                  <span class="description"><?php echo __('자동글 방지는 구글 reCAPTCHA API 를 사용합니다. 자동글 방지를 사용하기 위해서는 서버 클라이언트 URL 라이브러리(CURL)가 설치되어 있어야만 합니다. Site Key 와 Secret Key 를 생성하기 위해서는 구글 개발자 센터에서 등록하셔야 합니다.', 'kingkongboard');?> <a href="https://www.google.com/recaptcha/admin" target="_blank"><?php echo __('구글 reCAPTCHA API KEY 등록 바로가기', 'kingkongboard');?></a></span>
                </div>    
              </td>
            </tr>
            <tr>
              <th><?php echo __('썸네일', 'kingkongboard');?></th>
              <td>
                <input type="checkbox" name="kkb_board_thumbnail_display" value="display" <?php echo $thumb_dp_checked;?>> <?php echo __('리스트에 썸네일을 노출 합니다.', 'kingkongboard');?>
                <div class="description-container">
                  <span class="description"><?php echo __('게시판 리스트에 썸네일 노출 여부를 지정합니다.', 'kingkongboard');?></span>
                </div>                
              </td>
            </tr>
            <tr>
              <th><?php echo __('썸네일 본문', 'kingkongboard');?></th>
              <td>
                <input type="radio" name="kkb_board_thumbnail_input_content" value="T" <?php echo $thumb_con_include;?>> <?php echo __('본문포함', 'kingkongboard');?> <input type="radio" name="kkb_board_thumbnail_input_content" value="F" <?php echo $thumb_con_exclude;?>> <?php echo __('본문미포함', 'kingkongboard');?>
                <div class="description-container">
                  <span class="description"><?php echo __('본문포함을 체크할 경우 썸네일이 본문 상단에 자동으로 삽입 됩니다.', 'kingkongboard');?></span>
                </div>                
              </td>
            </tr>
            <tr>
              <th><?php echo __('검색 필터링:', 'kingkongboard');?></th>
              <td>
<?php
  if($board_search){

    $board_filter_on  = "";
    $board_filter_off = "";

    switch($board_search){
      case "T" :
        $board_filter_on = "checked";
      break;

      case "F" :
        $board_filter_off = "checked";
      break;
    }
  }
  $board_thumb_checked = null;
  $board_file_checked  = null;
  if($board_thumb_upload){
    if($board_thumb_upload == "T"){
      $board_thumb_checked = "checked";
    }
  }

  if($board_file_upload){
    if($board_file_upload == "T"){
      $board_file_checked = "checked";
    }
  }
?>
                <input type="radio" name="kkb_board_search_filter" value="T" <?php echo $board_filter_on;?>><?php echo __('포함', 'kingkongboard');?> <input type="radio" name="kkb_board_search_filter" value="F" <?php echo $board_filter_off;?>><?php echo __('미포함', 'kingkongboard');?>
                <div class="description-container">
                  <span class="description"><?php echo __('워드프레스 기본 검색 포함여부를 설정합니다.', 'kingkongboard');?></span>
                </div>
              </td>
            </tr>
            <tr>
              <th><?php echo __('첨부파일 설정:', 'kingkongboard');?></th>
              <td>
                <input type="checkbox" name="kkb_board_thumbnail_upload" value="T" <?php echo $board_thumb_checked;?>><?php echo __('썸네일 업로드 사용','kingkongboard');?> <input type="checkbox" name="kkb_board_file_upload" value="T" <?php echo $board_file_checked;?>><?php echo __('첨부파일 업로드 사용', 'kingkongboard');?>
                <div class="description-container">
                  <span class="description"><?php echo __('첨부파일 업로드 (썸네일 포함) 노출 여부를 지정합니다.', 'kingkongboard');?></span>
                </div>                
              </td>
            </tr>
            <tr>
              <th><?php _e('첨부파일 개수:', 'kingkongboard');?></th>
              <td>
                <input type="text" class="kkb-input" name="kkb_attach_number" value="<?php echo $attach_number;?>">
                <div class="description-container">
                  <span class="description"><?php echo __('올릴 수 있는 최대 첨부파일의 개수를 정의 합니다.', 'kingkongboard');?></span>
                </div>                 
              </td>
            </tr>
            <tr>
              <th><?php _e('댓글 HTML 사용:', 'kingkongboard');?></th>
              <td>
                <input type="checkbox" name="kkb_comment_html_use" value="T" <?php echo $comment_html_use_checked;?>><?php _e('댓글에 HTML 사용을 가능하게 합니다.', 'kingkongboard');?>
                <div class="description-container">
                  <span class="description"><?php echo __('댓글에 HTML 사용이 가능해지면 기본적인 방어 코드가 동작은 하지만 XSS 인젝션 해킹 공격에 취약할 수 있습니다. 가능한 댓글 HTML 사용을 추천드리지 않습니다.', 'kingkongboard');?></span>
                </div> 
              </td>
            </tr>
            <tr>
              <th><?php echo __('URL 자동 링크:', 'kingkongboard');?></th>
              <td>
                <input type="checkbox" name="kkb_auto_link" value="T" <?php echo $board_auto_link_selected;?>> <?php echo __('본문에 URL 기입시 자동으로 하이퍼링크를 붙입니다.', 'kingkongboard');?>
                <div class="description-container">
                  <span class="description"><?php echo __('http 또는 https 링크 기입시 자동으로 해당 경로로 링크를 연결 합니다.', 'kingkongboard');?></span>
                </div> 
                <input type="checkbox" name="kkb_comment_auto_link" value="T" <?php echo $board_comment_auto_link_checked;?>> <?php echo __('댓글에 URL 기입시 자동으로 하이퍼링크를 붙입니다.', 'kingkongboard');?>
                <div class="description-container">
                  <span class="description"><?php echo __('http 또는 https 링크 기입시 자동으로 해당 경로로 링크를 연결 합니다.', 'kingkongboard');?></span>
                </div>                 
              </td>
            </tr>
            <tr>
              <th><?php _e('아이프레임 모드:', 'kingkongboard');?></th>
              <td>
                <input type="checkbox" name="kkb_iframe_use" value="T" <?php echo $board_iframe_use_selected;?>> <?php _e('아이프레임 보기를 활성화 합니다.', 'kingkongboard');?>
                <div class="description-container">
                  <span class="description"><?php echo __('원페이지 테마나 페이지길이가 길다면 활성화 하세요. 특별한 문제가 없다면 비활성화로 두시기 바랍니다.', 'kingkongboard');?></span>
                </div>                 
              </td>
            </tr>
            <tr>
              <td colspan="2">
                <hr>
              </td>
            </tr>
            <tr>
              <th><?php echo __('기본양식설정', 'kingkongboard'); ?></th>
              <td>
                <?php 
                  $content = get_post_meta($board_id, "kkb_basic_form", true);
                  wp_editor($content, 'kkb_basic_form'); 
                ?>
                <div class="description-container">
                  <span class="description"><?php echo __('기본 글쓰기 시에 쓰일 양식을 지정합니다.', 'kingkongboard');?></span>
                </div>   
              </td>
            </tr>
            <tr>
              <th><?php echo __('금칙어 설정', 'kingkongboard');?></th>
              <td>
                <textarea rows="5" class="kkb-input" name="kkb_exclude_keyword" style="max-width:100%; width:100%; font-size:14px"><?php echo $exclude_keyword; ?></textarea>
                <div class="description-container">
                  <span class="description"><?php echo __('글쓰기시에 지정된 단어가 들어가 있으면 글이 등록되지 않습니다. 콤마(,)로 분리 합니다. 과도한 금칙어 설정과 일반적 단어 설정으로 인한 부작용에 주의하시기 바랍니다.', 'kingkongboard');?></span>
                </div>  
              </td>
            </tr>
            <tr>
              <td colspan="2">
                <hr>
              </td>
            </tr>
          </table>
          <br><br>
          <button type="button" class="button-kkb kkbgreen button-kkb-create-board"><i class="kkb-icon kkb-icon-setting"></i><?php echo __('수정 완료', 'kingkongboard');?></button>
          <a href="?page=KingkongBoard" class="button-kkb kkbred"><i class="kkb-icon kkb-icon-close" style="position:relative; top:5px"></i><?php echo __('취소', 'kingkongboard');?></a>
        </div>
      </div>
      <div class="settings-panel-right">
        <?php do_action('kingkong_board_columns'); ?>
      </div>
      <?php do_action('kkb_extend_columns', $board_id); ?>
    </div>
  </form>
  <script>
    jQuery(document).ready(function(){
      jQuery("[name=kkb_board_editor]").val("<?php echo $board_editor;?>");
    });
  </script>
<?php
  }
?>