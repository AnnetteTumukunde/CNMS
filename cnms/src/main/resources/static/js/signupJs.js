//Form Validation with jQuery validate
$('#signup-form').validate ({
    errorPlacement: function(error, element) {
        element.parent().find(".help-error-text").html(error);
    },
    rules: {
        Email: {
            required: true,
        },
        Password: {
            required: true,
            minlength: 4
        },
        highlight: function(element, errorClass) {
            $(element).removeClass(errorClass);
        }
    }
});

// Show password functionality with hideShowPassword
$('#password').hideShowPassword(false, 'focus', {
    toggle: {
        className: 'password-toggle',
        offset: 8,
        styles: { top: '0.5rem', margin: '0'}
    },
    wrapper: {
        element: false,
        enforceWidth: false,
        styles: { width: '100%',  }
    }
});