<template>
    <div class="dashboard">
        <ul>
            <li><router-link :to="{ name: 'article-list' }">ARTICLE</router-link></li>
            <li><router-link :to="{ name: 'tag-list' }">TAG</router-link></li>
        </ul>
    </div>
</template>
