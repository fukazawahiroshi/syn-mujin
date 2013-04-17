$("#initialList").append("<h3 style=\"text-decoration:underline;\">検索条件（担当者氏名、部署名等）を入力してください。</h3>");
$("#initialList").append("<span style=\"text-decoration:underline;\">検索文字:  </span>");
	
// 出力用テキストボックスの設置
var tb = document.createElement("input");
{
	tb.type         = "text";
	tb.name         = "search";
	tb.id           = "text_0";
//		tb.ng-model     = "thisSearch";
	tb.size         = 30;
	tb.placeholder  = "検索文字入力欄";
	tb.value        = "";
	tb.style.height = '30px';
	tb.style.width  = '400px';
	tb.style.color  = 'gray';
	tb.disabled     = true;
}

$("#initialList").append(tb);
$("#initialList").append("<br />");
$("#initialList").append("<br />");


// 入力用キーボードの作成
kana_char  = "ワラヤマハナタサカア";
kana_char += "ヲリユミヒ二チシキイ";
kana_char += "ンルヨムフヌツスクウ";
kana_char += "Sレ゛メヘネテセケエ";
kana_char += "Bロ゜モホノトソコオ";

// 文字入力処理用（小文字除く）
for (i=0; i < kana_char.length; i++)
{
	// キャラクター文字をjに保持
	var j = kana_char.charAt(i);
	
	if(j=="B") // BackSpace処理用
	{
		var widget = document.createElement("input");
		{
			widget.type         = "button";
			widget.id           = "button_BS";
			widget.value        = "←";
			widget.style.height = '50px';
			widget.style.width  = '50px';

			// コールバック関数の動的生成
			widget.onclick = new Function
			(
				  "var wid = document.getElementById('text_0');"
				+ "if(wid.disabled) {"
				+ " wid.value=''; wid.disabled=null;"
				+ "} else wid.value = wid.value.slice(0,-1);"
			);
		}
		
	}
	// 小文字処理用？（未使用）
	else if(j=="S")
	{
		// html部品種別
		var widget = document.createElement("input");
		{
			widget.type         = "button";        // ウィジェット種別
			widget.id           = "button_komoji"; // ボタンの識別ID
			widget.value        = "小";            // ボタンのラベル
			widget.style.height = '50px';
			widget.style.width  = '50px'; 
			widget.onclick      = function()
			{
				var wid = document.getElementById('text_0');
				switch(j)
				{
					case "ア":
						document.getElementById("text_0").value = "ァ";
         				//return "ァ";
         				
					case "イ":
						return "ィ";
						
					case "ウ":
         				return "ゥ";
         				
    				case "エ":
         				return "ェ";
         				
      				case "オ":
	    				return "ォ";
	    				
      				case "ヤ":
         				return "ャ";
         				
      				case "ユ":
        				return "ュ";
        				
      				case "ヨ":
         				return "ョ";
         				
      				case "ツ":
        				return "ｯ";
				}
				return wid.value = j;
			}
		}
	}
	else
	{
		// ボタン部品の動的作成
		// html部品種別
		var widget = document.createElement("input");
		{
			widget.type         = "button";      // ウィジェット種別
			widget.id           = "button_" + j; // ボタンの識別ID
			widget.value        = j;             // ボタンのラベル
			widget.style.height = '50px';
			widget.style.width  = '50px';
			
			// コールバック関数の動的生成
			widget.onclick = new Function
			(
				  "var wid = document.getElementById('text_0');"
				+ "if(wid.disabled) {"
				+ " wid.value=''; wid.value += '" + j + "'; wid.disabled=null"
				+ "} else wid.value +='" + j + "';"
			);
		}
	}
	// ボタンをbodyに追加
	$("#initialList").append(widget);
	
	// ボタンを5つ表示したら改行
	if(i%10 == 9) $("#initialList").append("<br />");
}


// 文字入力処理用（小文字除く）
kana_char_small = "ｮｭｬｯｫｪｩｨｧ";
for (i=0; i < kana_char_small.length; i++)
{
	// キャラクター文字をjに保持
	var j = kana_char_small.charAt(i);
	
	// ボタン部品の動的作成
	// html部品種別
	var widget = document.createElement("input");
	{
		widget.type             = "button";      // ウィジェット種別
		widget.id               = "button_" + j; // ボタンの識別ID
		widget.value            = j;             // ボタンのラベル
		widget.style.height     = '50px';
		widget.style.width      = '50px';
		widget.style.fontWeight = '20px';
		
		// コールバック関数の動的生成
		widget.onclick = new Function
		(
			  "var wid = document.getElementById('text_0');"
			+ "if(wid.disabled) {"
			+ " wid.value=''; wid.value += '" + j + "'; wid.disabled=null"
			+ "} else wid.value +='" + j + "';"
		);
	}
	// ボタンをbodyに追加
	$("#initialList").append(widget);
}
$("#initialList").append("<br />");
$("#initialList").append("<br />");


//文字入力処理用（小文字除く）
alpha_char = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
for (i=0; i < alpha_char.length; i++)
{
	// キャラクター文字をjに保持
	var j = alpha_char.charAt(i);
	
	// ボタン部品の動的作成
	// html部品種別
	var widget = document.createElement("input");
	{
		widget.type             = "button";      // ウィジェット種別
		widget.id               = "button_" + j; // ボタンの識別ID
		widget.value            = j;             // ボタンのラベル
		widget.style.height     = '50px';
		widget.style.width      = '50px';
		widget.style.fontWeight = '20px';
		
		// コールバック関数の動的生成
		widget.onclick = new Function
		(
			  "var wid = document.getElementById('text_0');"
			+ "if(wid.disabled) {"
			+ " wid.value=''; wid.value += '" + j + "'; wid.disabled=null"
			+ "} else wid.value +='" + j + "';"
		);
	}
	// ボタンをbodyに追加
	$("#initialList").append(widget);
	
	if(i%13 == 12) $("#initialList").append("<br />");
}


//文字入力処理用（小文字除く）
alpha_char_small = "abcdefghijklmnopqrstuvwxyz";
for (i=0; i < alpha_char_small.length; i++)
{
	// キャラクター文字をjに保持
	var j = alpha_char_small.charAt(i);
	
	// ボタン部品の動的作成
	// html部品種別
	var widget = document.createElement("input");
	{
		widget.type             = "button";      // ウィジェット種別
		widget.id               = "button_" + j; // ボタンの識別ID
		widget.value            = j;             // ボタンのラベル
		widget.style.height     = '50px';
		widget.style.width      = '50px';
		widget.style.fontWeight = '20px';
		
		// コールバック関数の動的生成
		widget.onclick = new Function
		(
			  "var wid = document.getElementById('text_0');"
			+ "if(wid.disabled) {"
			+ " wid.value=''; wid.value += '" + j + "'; wid.disabled=null"
			+ "} else wid.value +='" + j + "';"
		);
	}
	// ボタンをbodyに追加
	$("#initialList").append(widget);
	
	if(i%13 == 12) $("#initialList").append("<br />");
}
$("#initialList").append("<br />");

// 検索ボタン
var search_bt = document.createElement("input");
{
	search_bt.type         = "button";
	search_bt.id           = "search_button_0";
	search_bt.value        = "検索";
	search_bt.style.width  = '150px';
	search_bt.style.height = '50px';
	search_bt.style.color  = 'blue';
	search_bt.onclick      = function()
	{
		showInitialList($("#text_0").val());
	};
}
$("#initialList").append(search_bt);

// テキストボックスのクリアボタン
var cancel_bt = document.createElement("input");
{
	cancel_bt.type         = "button";
	cancel_bt.id           = "cancel_button_0";
	cancel_bt.value        = "キャンセル";
	cancel_bt.style.width  = '150px';
	cancel_bt.style.height = '50px';
	cancel_bt.style.color  = 'red';
	cancel_bt.onclick      = function()
	{
		console.log("text_clear");
		document.getElementById("text_0").value = "";
	};
}
$("#initialList").append(cancel_bt);