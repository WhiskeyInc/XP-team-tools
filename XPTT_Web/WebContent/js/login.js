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
														message : 'The name is required and cannot be empty'
													}
												}
											},
											password : {
												validators : {
													notEmpty : {
														message : 'The description is required and cannot be empty'
													}
												}
											}
										}
									});
				});