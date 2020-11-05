(function($){
    $.fn.extend({
        donetyping: function(callback,timeout){
            timeout = timeout || 500;
            var timeoutReference,
                doneTyping = function(el){
                    if (!timeoutReference) return;
                    timeoutReference = null;
                    callback.call(el);
                };
            return this.each(function(i,el){
                var $el = $(el);
                $el.is(':input') && $el.on('keyup keypress',function(e){
                    if (e.type=='keyup' && e.keyCode!=8) return;
                    if (timeoutReference) clearTimeout(timeoutReference);
                    timeoutReference = setTimeout(function(){
                        doneTyping(el);
                    }, timeout);
                }).on('blur',function(){
                    doneTyping(el);
                });
            });
        }
    });
})(jQuery);

formValidation = {
	init: function(){
		this.$form = $('.registration-form');
		this.$firstName = this.$form.find('input[name="name"]');
		this.$lastName = this.$form.find('input[name="surname"]');
		this.$email = this.$form.find('input[name="email"]');
		this.$password = this.$form.find('input[name="password"]');
		this.$passwordToggle = this.$form.find('button.toggle-visibility');
		this.$submitButton = this.$form.find('button.submit');
		this.bindEvents();
	},
	bindEvents: function(){
		this.$passwordToggle.mousedown(this.togglePasswordVisibilityHandler.bind(this));
		this.$passwordToggle.click(function(e){e.preventDefault()});
		this.$form.submit(this.submitFormHandler.bind(this));
	},
	togglePasswordVisibilityHandler: function(){
		var html = '<input type="text" value="'+this.$password.val()+'">';
		var $passwordParent = this.$password.parent()
		var saved$password = this.$password.detach();
		$passwordParent.append(html);
		this.$passwordToggle.find('span').removeClass('glyphicon-eye-close').addClass('glyphicon-eye-open');
		this.$passwordToggle.one('mouseup mouseleave', (function(){
			$passwordParent.find('input').remove();
			$passwordParent.append(saved$password);
			this.$passwordToggle.find('span').removeClass('glyphicon-eye-open').addClass('glyphicon-eye-close');
		}).bind(this));
	}
}.init();