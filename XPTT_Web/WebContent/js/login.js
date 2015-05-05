$(document)
		.ready(
				function() {
					$('#signIn')
							.bootstrapValidator(
									{
										// live: 'disabled',
										framework: 'bootstrap',
										message : 'This value is not valid',
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											userId : {
												validators : {
													notEmpty : {
														message : 'The userId is required and cannot be empty'
													},
													regexp : {
														regexp: /^[\w]+$/,
								                        message: 'You can introduce just alphabetical characters, underscore, number but no spaces' 
													}
												}
											},
											password : {
												validators : {
													notEmpty : {
														message : 'The password is required and cannot be empty'
													},
													regexp : {
														regexp: /^[\w]+$/,
								                        message: 'You can introduce just alphabetical characters, underscore, number but no spaces' 
													}
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
								framework: 'bootstrap',
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
												message : 'The userName is required and cannot be empty'
											}
										}
									},
									userId : {
										validators : {
											notEmpty : {
												message : 'The userId is required and cannot be empty'
											},
											regexp : {
												regexp: /^[\w]+$/,
						                        message: 'You can introduce just alphabetical characters, underscore, number but no spaces' 
											}
										}
									},
									password : {
										validators : {
											notEmpty : {
												message : 'The password is required and cannot be empty'
											},
											regexp : {
												regexp: /^[\w]+$/,
						                        message: 'You can introduce just alphabetical characters, underscore, number but no spaces' 
											}
										}
									}
								}
							});
		});