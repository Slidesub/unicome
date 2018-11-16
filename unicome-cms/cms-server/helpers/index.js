exports.isEmpty = (value) => {
    if (value === undefined || value === '' || value === null) {
        return true
    }
    return false
}