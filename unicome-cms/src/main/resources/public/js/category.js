'use strict'

Vue.component('item', {
    template: '<li>' +
               '<div' +
                 ' :style="{color: isFolder? red: green}"' +
                 ' @click="toggle"' +
                 ' @dblclick="changeType">' +
                 '{{ model.name }}' +
                 '<span v-if="isFolder">[{{ open ? "-" : "+" }}]</span>' +
               '</div>' +
               '<ul v-show="open" v-if="isFolder">' +
                 '<item' +
                   ' class="item"' +
                   ' v-for="(model, index) in model.children"' +
                   ' :key="index"' +
                   ' :model="model">' +
                 '</item>' +
                 '<li class="add" @click="addChild">+</li>' +
               '</ul>' +
             '</li>',
    props: {
        model: Object
    },
    data: function () {
        return {
            red: 'red',
            green: 'green',
            open: false
        }
    },
    computed: {
        isFolder: function () {
            return this.model.children && this.model.children.length
        }
    },
    methods: {
        toggle: function () {
            if (this.isFolder) {
                this.open = !this.open
            }
        },
        changeType: function () {
            if (!this.isFolder) {
              Vue.set(this.model, 'children', [])
              this.addChild()
              this.open = true
            }
         },
         addChild: function () {
            this.model.children.push({
              name: 'new stuff'
            })
         }
    }
})