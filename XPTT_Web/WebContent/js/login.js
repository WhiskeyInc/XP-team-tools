$(document)
		.ready(
				function() {
					$('#signIn')
							.bootstrapValidator(
									{
										// live: 'disabled',
										framework : 'bootstrap',
										message : 'This value is not valid',
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											userName : {
												validators : {
													notEmpty : {
														message : 'The user name is required and cannot be empty'
													},
												}
											},
											password : {
												validators : {
													notEmpty : {
														message : 'The password is required and cannot be empty'
													},
												}
											}
										}
									});
				});
$(document)
		.ready(
				function() {
					$('#signUp')
							.bootstrapValidator(
									{
										// live: 'disabled',
										framework : 'bootstrap',
										message : 'This value is not valid',
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											userName : {
												validators : {
													notEmpty : {
														message : 'The user name is required and cannot be empty'
													},
												}
											},
											password : {
												validators : {
													notEmpty : {
														message : 'The password is required and cannot be empty'
													},
												}
											}
										}
									});
				});