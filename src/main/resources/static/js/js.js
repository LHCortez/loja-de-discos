function confirmDeleteBand(event, id, nome) {
    event.preventDefault();
    Swal.fire({
        title: ("Excluir " + nome + "?"),
        text: "Isto fará com que todos os produtos relacionados à banda também sejam excluídos.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#499927',
        cancelButtonText: 'Não, cancelar',
        confirmButtonText: 'Sim, excluir'
    }).then((result) => {
        if (result.isConfirmed) {
            $("#delete-band-" + id).submit();
        }
    });
}

function confirmDeleteProduct(event, id, nome) {
    event.preventDefault();
    Swal.fire({
        title: "Excluir produto?",
        text: ("Tem certeza que deseja excluir o produto " + nome + "?"),
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#499927',
        cancelButtonText: 'Não, cancelar',
        confirmButtonText: 'Sim, excluir'
    }).then((result) => {
        if (result.isConfirmed) {
            $("#delete-product-" + id).submit();
        }
    });
}

function filtro(inputName, tableName) {
    var input = document.getElementById(inputName);
    var table = document.getElementById(tableName);
    var filter = input.value.toLowerCase();
    $("#" + tableName + " tr").filter(function() {
        $(this).toggle($(this).text().toLowerCase().indexOf(filter) > -1)
    });
}



