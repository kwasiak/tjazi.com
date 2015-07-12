var fs = require("fs");
var projectStructure = JSON.parse(fs.readFileSync("project_structure.json"));

module.exports = function (grunt) {

    grunt.initConfig({

        jshint: {
            files: ['Gruntfile.js', projectStructure.appJsFiles],
            globals: {
                console: true,
                module: true,
                document: true
            }
        },

        less: {
            development: {
                options: {
                    compress: false,
                    yuicompress: false,
                    optimization: 2
                },
                files: projectStructure.lessToCssFiles
            },
            production: {
                options: {
                    compress: true,
                    yuicompress: true,
                    optimization: 2
                },
                files: projectStructure.lessToCssFiles
            }
        },

        concat: {
            options: {
                separator: ';'
            },
            development: {
                // the output files for development are the same as for uglify output
                // uglify will not be run for development
                files: [
                    {
                        src: projectStructure.libJsFiles,
                        dest: projectStructure.outputMinifyLibsJsPath
                    },
                    {
                        src: projectStructure.appJsFiles,
                        dest: projectStructure.outputMinifyAppJsPath
                    }
                ]
            },
            production: {
                files: [
                    {
                        src: projectStructure.libJsFiles,
                        dest: projectStructure.outputConcatLibsJsPath
                    },
                    {
                        src: projectStructure.appJsFiles,
                        dest: projectStructure.outputConcatAppJsPath
                    }
                ]
            }
        },

        uglify: {
            options: {
                /* options placeholder */
            },
            application: {
                files: [
                    {
                        src: projectStructure.outputConcatLibsJsPath,
                        dest: projectStructure.outputMinifyLibsJsPath
                    }
                ]
            },

            libraries: {
                files: [
                    {
                        src: projectStructure.outputConcatAppJsPath,
                        dest: projectStructure.outputMinifyAppJsPath
                    }
                ]
            }
        }
    });

    // Load the plugin that provides the "uglify" task.
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-less');

    // Default task(s).
    grunt.registerTask('default', ['jshint', 'less:development', 'concat:development']);

    grunt.registerTask('development', ['default']);
    grunt.registerTask('production', ['jshint', 'less:production', 'concat:production', 'uglify']);
};

