module.exports = function(grunt) {
	grunt.initConfig({ 
		pkg: grunt.file.readJSON('package.json'),
		uglify: {
			options: {
				banner: '/*\n <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> \n*/\n'
		    },
		    omega: {
		    	files: {
		    		'WebContent/resources/javascript/omega.min.js': ['WebContent/resources/javascript/omega.js']
		    	}
		    }
		},
		clean: {
		  jquery: {
		    src: ['WebContent/resources/jquery']
		  },
		  bootstrap: {
		    src: ['WebContent/resources/bootstrap']
		  },
		  angular: {
		    src: ['WebContent/resources/angular']
		  }
		}
		,copy: {
			jquery: {
				files: [
					{   expand: true,
						cwd: 'bower_components/jquery/',
						src: ['**'], 
						dest: 'WebContent/resources/jquery/'
					}
				]
			},
			bootstrap: {
				files: [
					{   expand: true,
						cwd: 'bower_components/bootstrap/',
						src: ['**'], 
						dest: 'WebContent/resources/bootstrap/'
					}
				]
			},
			bootbox: {
				files: [
					{   expand: true,
						cwd: 'bower_components/bootbox.js/',
						src: ['**'], 
						dest: 'WebContent/resources/bootbox/'
					}
				]
			},
			angular: {
				files: [
					{   expand: true,
						cwd: 'bower_components/angular/',
						src: ['**'], 
						dest: 'WebContent/resources/angular/'
					},
					{   expand: true,
						cwd: 'bower_components/angular-route/',
						src: ['**'], 
						dest: 'WebContent/resources/angular/'
					}
				]
			}
		}
	});
	
	// grunt.loadNpmTasks('grunt-contrib-less');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-copy');
};
