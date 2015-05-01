$('#projectAdder').bootstrapValidator({
	// live: 'disabled',
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		projectName : {
			validators : {
				notEmpty : {
					message : 'The name is required and cannot be empty'
				}
			}
		},
		description : {
			validators : {
				notEmpty : {
					message : 'The description is required and cannot be empty'
				}
			}
		}
	}
});