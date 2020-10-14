(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define( ["jquery", "../jquery.validate"], factory );
	} else if (typeof module === "object" && module.exports) {
		module.exports = factory( require( "jquery" ) );
	} else {
		factory( jQuery );
	}
}(function( $ ) {

/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
$.extend( $.validator.messages, {
	required: "Đây là một lĩnh vực cần thiết",
	remote: "Vui lòng sửa trường này",
	email: "Vui lòng nhập một địa chỉ email hợp lệ",
	url: "Vui lòng nhập một URL hợp lệ",
	date: "Vui lòng nhập một ngày hợp lệ",
	dateISO: "Vui lòng nhập một ngày hợp lệ (YYYY-MM-DD)",
	number: "Vui lòng nhập một số hợp lệ",
	digits: "Chỉ nhập số",
	creditcard: "Vui lòng nhập số thẻ tín dụng hợp lệ",
	equalTo: "Đầu vào của bạn không giống nhau",
	extension: "Vui lòng nhập một hậu tố hợp lệ",
	maxlength: $.validator.format( "Bạn có thể nhập nhiều nhất {0} Nhân vật" ),
	minlength: $.validator.format( "Ít nhất đầu vào {0} Nhân vật" ),
	rangelength: $.validator.format( "Vui lòng nhập độ dài vào {0} Đến {1} Chuỗi giữa" ),
	range: $.validator.format( "Vui lòng nhập phạm vi {0} Đến {1} Giữa" ),
	step: $.validator.format( "Vui lòng nhập bội số nguyên của {0}" ),
	max: $.validator.format( "Vui lòng nhập giá trị không lớn hơn {0}" ),
	min: $.validator.format("Vui lòng nhập giá trị không nhỏ hơn {0}" )
} );
return $;
}));