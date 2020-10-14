/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/

define(function(require, exports, module) {
    var J = jQuery, _BATH = _MTONS.BASE_PATH;

    var _configs = {
        errorElement: "p",
        errorPlacement: function (error, element) {
            error.addClass("help-block");
            if ( element.prop( "name" ) === "email" ) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        },
        highlight: function (element, errorClass, validClass) {
            J(element).closest("div").addClass("has-error").removeClass("has-success");
        },
        unhighlight: function (element, errorClass, validClass) {
            J(element).closest("div").addClass("has-success").removeClass("has-error");
        }
    };

    var _bind_validate = function (formId, configs) {
        var options = J.extend({}, _configs, configs);

        require.async(['validation', 'validation-additional'], function () {
            J(formId).validate(options);
        });
    };

    var Validate = {
        register: function (formId, sendCodeButtonId) {
            _bind_validate(formId, {
                rules: {
                    username: {
                        required: true,
                        check_username: true
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    code: {
                        required: true
                    },
                    password: {
                        required: true
                    },
                    password2: {
                        required: true,
                        equalTo: "#password"
                    }
                },
                messages: {
                    username: {
                        required: 'Vui lòng nhập tên người dùng',
                        check_username: 'Chỉ các chữ cái / chữ cái + số, không ít hơn 5 chữ số'
                    },
                    email: {
                        required: 'Vui lòng nhập địa chỉ email',
                        email: 'Định dạng e-mail không chính xác'
                    },
                    code: {
                        required: 'Vui lòng nhập mã xác minh nhận được'
                    },
                    password: {
                        required: 'Xin vui lòng nhập mật khẩu'
                    },
                    password2: {
                        required: 'Vui lòng nhập mật khẩu xác nhận',
                        equalTo: 'Hai mật khẩu đã nhập không đúng'
                    }
                }
            });

            J(sendCodeButtonId).click(function () {
                var btn = J(this).button('sending');
                var email = J('input[name=email]').val();
                J.getJSON(_BATH + '/email/send_code', {'email': email, 'type': 3}, function (data) {
                    if (data.code === 0) {
                        btn.text('Gửi lại');
                        J('#message').html('<div class="alert alert-success">' + data.message + '</div>');
                    } else {
                        J('#message').html('<div class="alert alert-danger">' + data.message + '</div>');
                    }

                    btn.button('reset');
                });
            });
        },
        oauthRegister: function (formId) {
            _bind_validate(formId, {
                rules: {
                    username: {
                        required: true,
                        check_username: true
                    }
                },
                messages: {
                    username: {
                        required: 'Vui lòng nhập tên người dùng',
                        check_username: 'Chỉ các chữ cái / chữ cái + số, không ít hơn 5 chữ số'
                    }
                }
            });
        },
        forgot: function (formId, sendCodeButtonId) {
            J(sendCodeButtonId).click(function () {
                var btn = J(this).button('sending');
                var email = J('input[name=email]').val();
                J.getJSON(_BATH + '/email/send_code', {'email': email, 'type': 2}, function (data) {
                    if (data.code === 0) {
                        btn.text('Gửi lại');
                        J('#message').html('<div class="alert alert-success">' + data.message + '</div>');
                    } else {
                        J('#message').html('<div class="alert alert-danger">' + data.message + '</div>');
                    }

                    btn.button('reset');
                });
            });

            _bind_validate(formId, {
                rules: {
                    email: {
                        required: true,
                        email: true
                    },
                    password: 'required',
                    code: 'required',
                    password2: {
                        required: true,
                        equalTo: "#password"
                    }
                },
                messages: {
                    email: {
                        required: 'Vui lòng nhập địa chỉ email',
                        email: 'Định dạng e-mail không chính xác'
                    },
                    password: 'Vui lòng nhập mật khẩu mới',
                    code: 'Vui lòng nhập mã xác minh nhận được',
                    password2: {
                        required: 'Vui lòng nhập mật khẩu xác nhận',
                        equalTo: 'Hai mật khẩu đã nhập không đúng'
                    }
                }
            });
        },
        updateEmail: function (formId, sendCodeButtonId) {
            _bind_validate(formId, {
                rules: {
                    email: {
                        required: true,
                        email: true
                    },
                    code: {
                        required: true
                    }
                },
                messages: {
                    email: {
                        required: 'Vui lòng nhập địa chỉ email',
                        email: 'Định dạng e-mail không chính xác'
                    },
                    code: {
                        required: 'Vui lòng nhập mã xác minh nhận được'
                    }
                }
            });

            J(sendCodeButtonId).click(function () {
                var btn = J(this).button('sending');
                var email = J('input[name=email]').val();
                J.getJSON(_BATH + '/email/send_code', {'email': email, 'type': 1}, function (data) {
                    if (data.code === 0) {
                        btn.text('Gửi lại');
                        J('#message').html('<div class="alert alert-success">' + data.message + '</div>');
                    } else {
                        J('#message').html('<div class="alert alert-danger">' + data.message + '</div>');
                    }
                    btn.button('reset');
                });
            });
        },

        updatePassword: function (formId) {
            _bind_validate(formId, {
                rules: {
                    oldPassword: 'required',
                    password: 'required',
                    password2: {
                        required: true,
                        equalTo: "#password"
                    }
                },
                messages: {
                    oldPassword: 'Vui lòng nhập mật khẩu hiện tại',
                    password: 'Vui lòng nhập mật khẩu mới',
                    password2: {
                        required: 'Vui lòng nhập mật khẩu xác nhận',
                        equalTo: 'Hai mật khẩu đã nhập không đúng'
                    }
                }
            });
        },

        updateProfile: function (formId) {
            _bind_validate(formId, {
                rules: {
                    name: 'required'
                },
                messages: {
                    name: 'Hãy nhập một nickname'
                }
            });
        }
    };

    module.exports = Validate;
});